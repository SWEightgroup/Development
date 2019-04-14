package it.colletta.service.user;

import it.colletta.model.ExerciseModel;
import it.colletta.model.SignupRequestModel;
import it.colletta.model.UserModel;
import it.colletta.repository.user.UsersRepository;
import it.colletta.security.ParseJwt;
import it.colletta.security.Role;
import it.colletta.service.signup.SignupRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

@Service
public class UserService {

  @Autowired
  private UsersRepository applicationUserRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  /**
   * Constructor.
   *
   * @param usersRepository UserReposytory
   * @param passwordEncoder bCryptPasswordEncoder
   */
  UserService(final UsersRepository usersRepository, final BCryptPasswordEncoder passwordEncoder) {
    this.applicationUserRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Add new user.
   *
   * @param user User
   * @return added user
   */
  public UserModel addUser(@NotNull UserModel user) {
    SignupRequestService signupRequestService = new SignupRequestService();
    final String encode = passwordEncoder.encode(user.getPassword());
    user.setPassword(encode);
    user.setEnabled(false);
    user = applicationUserRepository.save(user);
    SignupRequestModel signupRequestModel = SignupRequestModel.builder().userReference(user.getId())
        .requestDate(Calendar.getInstance().getTime()).build();
    // signupRequestService.addSignUpRequest(signupRequestModel);
    user.setPassword(null);
    return user;
  }

  public Optional<UserModel> findById(final String userId) {
    return applicationUserRepository.findById(userId);
  }

  /**
   * Return user information.
   *
   * @param id User id
   * @return User
   */
  public UserModel getUserInfo(final String id) {
    Optional<UserModel> userModelOptional = applicationUserRepository.findById(id);
    if (userModelOptional.isPresent()) {
      return userModelOptional.get();
    } else {
      throw new UsernameNotFoundException("Id not refer to a user of the system");
    }
  }

  /**
   * Active an user.
   *
   * @param id User id
   */
  public void activateUser(final String id) {
    applicationUserRepository.updateActivateFlagOnly(id);
  }

  /**
   * Delete user.
   *
   * @param userId User id
   * @return user deleted
   */
  public UserModel deleteUser(final String userId) {
    Optional<UserModel> userToDelete = applicationUserRepository.findById(userId);
    if (userToDelete.isPresent()) {
      applicationUserRepository.delete(userToDelete.get());
      return userToDelete.get();
    } else {
      throw new UsernameNotFoundException("Id not found");
    }
  }

  /**
   * Return user by Email.
   *
   * @param email User email
   * @return User
   */
  public UserModel findByEmail(final String email) {
    return applicationUserRepository.findByEmail(email);
  }

  /**
   * Update user info.
   *
   * @param newUserData User info
   * @param token User token
   * @return User
   */
  public UserModel updateUser(final UserModel newUserData, final String token) {

    String email = ParseJwt.getEmailFromJwt(token);
    // String newEmail = newUserData.getUsername();
    /*
     * if (!email.equals(newEmail) && applicationUserRepository.findByEmail(newEmail) != null) { //
     * ho modificato la mia mail throw new NotOwnerException(); }
     */
    UserModel user = applicationUserRepository.findByEmail(email);

    Optional<String> newFirstName = Optional.ofNullable(newUserData.getFirstName());
    if (newFirstName.isPresent()) {
      user.setFirstName(newFirstName.get());
    }
    Optional<String> newLastName = Optional.ofNullable(newUserData.getLastName());
    if (newLastName.isPresent()) {
      user.setLastName(newLastName.get());
    }
    Optional<String> newLanguageName = Optional.ofNullable(newUserData.getLanguage());
    if (newLanguageName.isPresent()) {
      user.setLanguage(newLanguageName.get());
    }
    Optional<Date> newDateOfBirth = Optional.ofNullable(newUserData.getDateOfBirth());
    if (newDateOfBirth.isPresent()) {
      user.setDateOfBirth(newDateOfBirth.get());
    }
    return applicationUserRepository.save(user);
  }

  /**
   * adds the exercise to the todo list of students.
   *
   * @param assignedUsersIds List of users
   * @param exerciseModel Exericse
   */
  public void addExerciseItem(final List<String> assignedUsersIds,
      final ExerciseModel exerciseModel) {
    Iterable<UserModel> users = applicationUserRepository.findAllById(assignedUsersIds);
    for (UserModel user : users) {
      user.addExerciseToDo(exerciseModel.getId()); // TODO se un esercizio ritorna false lancio
                                                   // eccezione
    }
    applicationUserRepository.saveAll(users);
  }

  /**
   * Return all todo id of exericses.
   *
   * @param userId User id
   * @return list of exercise id
   */
  public List<String> getAllExerciseToDo(final String userId) {
    Optional<UserModel> userModel = applicationUserRepository.findById(userId);
    if (userModel.isPresent()) {
      return userModel.get().getExercisesToDo();
    } else {
      throw new UsernameNotFoundException("User not found in the system");
    }
  }

  /**
   * Return all student user.
   *
   * @return list of students
   */
  public List<UserModel> getAllStudents() {
    return applicationUserRepository.findAllStudents();
  }

  /**
   * Return all user.
   *
   * @return List of students
   */
  public List<UserModel> getAllUsers() {
    return applicationUserRepository.getAllUsers();
  }

  /**
   * Delete an exercise.
   *
   * @param exerciseId Exercise id
   * @param userId User id
   * @return UserModel
   */
  public Optional<UserModel> deleteExerciseAssigned(final String exerciseId, final String userId) {
    Optional<UserModel> user = applicationUserRepository.findById(userId);
    if (user.isPresent()) {
      if (exerciseId.equals(user.get().getId()) && user.get().getRole().equals(Role.TEACHER)) {
        applicationUserRepository.deleteFromExerciseToDo(exerciseId);
      }
    }
    return Optional.empty();
  }

  /**
   * Return all done exercise.
   *
   * @param userid Student id
   * @return List of exericses
   */
  public List<String> getAllExerciseDone(final String userid) {
    Optional<UserModel> userModel = applicationUserRepository.findById(userid);
    if (userModel.isPresent()) {
      return userModel.get().getExercisesDone();
    } else {
      throw new UsernameNotFoundException("User not found in the system");
    }
  }

  /**
   * Return all done exercise.
   *
   * @param userid Student id
   * @return List of exericses
   */
  public List<String> getAllToDoByAuthorId(final String userid) {
    Optional<UserModel> userModel = applicationUserRepository.findById(userid);
    if (userModel.isPresent()) {
      return userModel.get().getExercisesToDo();
    } else {
      throw new UsernameNotFoundException("User not found in the system");
    }
  }

  /**
   * Shift an exercise from todo in done list.
   *
   * @param studentId Student Id
   * @param exerciseToCorrect Exercise
   */
  public void exerciseCompleted(final String studentId, final ExerciseModel exerciseToCorrect) {
    Optional<UserModel> userOptional = applicationUserRepository.findById(studentId);
    if (userOptional.isPresent()) {
      UserModel user = userOptional.get();
      user.removeExerciseToDo(exerciseToCorrect.getId());
      user.addExerciseDone(exerciseToCorrect.getId());
      applicationUserRepository.save(user);
    }
  }

  // TODO e' developer....
  /**
   * Return all developer user.
   *
   * @param userId Id of the applicant
   * @return User List
   */
  public List<UserModel> getAllDevelopmentToEnable(final String userId) {
    Optional<UserModel> user = applicationUserRepository.findById(userId);
    List<UserModel> mydevelopment = null;
    if (user.isPresent()) {
      // if (user.get().getRole().equals(Role.ADMIN)) {}
      mydevelopment = applicationUserRepository.findAllDeveloperDisabled();
    }
    return mydevelopment;
  }
}
