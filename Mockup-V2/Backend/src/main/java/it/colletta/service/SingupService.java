package it.colletta.service;

import it.colletta.model.SignupRequestModel;
import it.colletta.model.UserModel;
import it.colletta.repository.administration.SingupRequestRepository;
import it.colletta.repository.user.UsersRepository;
import it.colletta.service.signup.EmailServiceImpl;
import java.util.Calendar;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SingupService {

  private BCryptPasswordEncoder passwordEncoder;
  private SingupRequestRepository singupRequestRepository;
  private UsersRepository usersRepository;
  private EmailServiceImpl emailService;

  /**
   * Constructor.
   *
   * @param usersRepository UserReposytory
   * @param passwordEncoder bCryptPasswordEncoder
   */
  @Autowired
  public SingupService(BCryptPasswordEncoder passwordEncoder,
      SingupRequestRepository singupRequestRepository,
      UsersRepository usersRepository, EmailServiceImpl emailService) {
    this.passwordEncoder = passwordEncoder;
    this.singupRequestRepository = singupRequestRepository;
    this.usersRepository = usersRepository;
    this.emailService = emailService;
  }

  /**
   * Add new user.
   *
   * @param user User object
   * @return the new user
   */
  public UserModel addUser(@NotNull UserModel user, ControllerLinkBuilder link) throws Exception {
    // TD() <= TS()

    try {
      if (Objects.nonNull(user)) {
        final String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setEnabled(false);
        usersRepository.save(user);
        SignupRequestModel signupRequestModel = SignupRequestModel.builder()
            .userReference(user.getId())
            .requestDate(Calendar.getInstance().getTime()).build();
        SignupRequestModel model = singupRequestRepository.save(signupRequestModel);
        emailService.activateUserMail(user, link.slash(model.getId()).withSelfRel().getHref());
        user.setPassword(null);
      }
      return user;
    } catch (Exception error) {
      if (user.getId() != null) {
        usersRepository.deleteById(user.getId());
      }

      throw error;
    }
  }

  /**
   * @TODO Controllate questo metodo
   */
  public void setEnabledToTrue(String requestId) throws ResourceNotFoundException {
    SignupRequestModel requestModel = singupRequestRepository.findById(requestId)
        .orElseThrow(() -> new ResourceNotFoundException("Signup request not found"));

    // Date requestTimestamp = requestModel.getRequestDate();
    // if (requestTimestamp.compareTo(Calendar.getInstance().getTime()) < 1) {
    UserModel userToEnable = usersRepository.findById(requestModel.getUserReference())
        .orElseThrow(ResourceNotFoundException::new);
    userToEnable.setEnabled(true);
    usersRepository.save(userToEnable);
    // }
    singupRequestRepository.delete(requestModel);
  }
}
