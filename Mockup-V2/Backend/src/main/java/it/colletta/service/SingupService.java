package it.colletta.service;

import it.colletta.model.SignupRequestModel;
import it.colletta.model.UserModel;
import it.colletta.repository.administration.SingupRequestRepository;
import it.colletta.repository.user.UsersRepository;
import it.colletta.security.Role;
import it.colletta.service.signup.EmailServiceImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SingupService {

  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  private SingupRequestRepository singupRequestRepository;
  @Autowired
  private UsersRepository usersRepository;
  @Autowired
  private EmailServiceImpl emailService;

  /**
   * Constructor.
   *
   * @param usersRepository UserReposytory
   * @param passwordEncoder bCryptPasswordEncoder
   */
  public SingupService(final UsersRepository usersRepository,
      final SingupRequestRepository singupRequestRepository,
      final BCryptPasswordEncoder passwordEncoder) {
    this.usersRepository = usersRepository;
    this.singupRequestRepository = singupRequestRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Add new user.
   *
   * @param user User
   * @return added user
   */
  public UserModel addUser(@NotNull UserModel user, ControllerLinkBuilder link)
      throws org.springframework.dao.DuplicateKeyException{
    final String encode = passwordEncoder.encode(user.getPassword());
    user.setPassword(encode);
    user.setEnabled(false);
    if(user.getRole().equals(Role.STUDENT)) {
      user.setExercisesToDo(new ArrayList<String>());
      user.setExercisesDone(new ArrayList<String>());
      user.setFavoriteTeacherIds(new ArrayList<String>());
    }
    else if(user.getRole().equals(Role.TEACHER)) {
      user.setTeacherExercise(new ArrayList<String>());
    }
    user = usersRepository.save(user);
    SignupRequestModel signupRequestModel = SignupRequestModel.builder().userReference(user.getId())
        .requestDate(Calendar.getInstance().getTime()).build();
    SignupRequestModel model = singupRequestRepository.save(signupRequestModel);
    //emailService.activateUserMail(user, link.slash(model.getId()).withSelfRel().getHref());
    user.setPassword(null);
    return user;
  }

  public void setEnabledToTrue(String requestId) throws ResourceNotFoundException {
    Optional<SignupRequestModel> requestModel = singupRequestRepository.findById(requestId);
    if(requestModel.isPresent()) {
      Date requestTimestamp = requestModel.get().getRequestDate();
      if(requestTimestamp.compareTo(Calendar.getInstance().getTime()) < 1) {
        UserModel userToEnable = usersRepository.findById(requestModel.get().getUserReference()).get();
        userToEnable.setEnabled(true);
      }
      singupRequestRepository.delete(requestModel.get());
    }
    else {
      throw new ResourceNotFoundException("Signup request not found");
    }
  }
}
