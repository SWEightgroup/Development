package it.colletta.service;

import static it.colletta.security.Role.DEVELOPER;

import it.colletta.model.SignupRequestModel;
import it.colletta.model.UserModel;
import it.colletta.repository.administration.SingupRequestRepository;
import it.colletta.repository.user.UsersRepository;
import it.colletta.service.signup.EmailServiceImpl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;

import javax.validation.constraints.NotNull;

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
  public SingupService(BCryptPasswordEncoder passwordEncoder, SingupRequestRepository singupRequestRepository,
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
        Optional<UserModel> checkUser = usersRepository.findByEmail(user.getUsername());
        if (checkUser.isPresent())
          throw new Exception("This email has already been used");
        final String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setEnabled(false);
        user.setId(new ObjectId().toHexString());
        SignupRequestModel signupRequestModel = SignupRequestModel.builder().userToConfirm(user)
            .requestDate(Calendar.getInstance().getTime()).build();
        SignupRequestModel model = singupRequestRepository.save(signupRequestModel);
        emailService.activateUserMail(user, link.slash(model.getId()).withSelfRel().getHref());
        user.setPassword(null);
      }
      return user;
    } catch (Exception error) {
      error.printStackTrace();
      throw error;
    }
  }

  /**
   * setEnabledToTrue.
   */
  public void setEnabledToTrue(String requestId) throws ResourceNotFoundException {
    SignupRequestModel requestModel = singupRequestRepository.findById(requestId)
        .orElseThrow(() -> new ResourceNotFoundException("Signup request not found"));
    UserModel userToEnable = requestModel.getUserToConfirm();
    if (!userToEnable.getRole().equals(DEVELOPER)) {
      userToEnable.setEnabled(true);
    }
    singupRequestRepository.delete(requestModel);
    usersRepository.save(userToEnable);
  }
}
