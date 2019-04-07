package it.colletta.controller;

import it.colletta.model.ExerciseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.CorrectionHelper;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.security.ParseJWT;
import it.colletta.service.ExerciseService;
import it.colletta.service.SolutionService;
import it.colletta.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.acl.NotOwnerException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {

  @Autowired
  UserService userService;

  @Autowired
  ExerciseService exerciseService;

  @Autowired
  @Lazy
  SolutionService solutionService;

  // TODO: mettere @PreAuthorize e controllare ruolo admin
  /**
   * 
   * @param userId the user unique id
   * @return the user deleted by the admin.
   */
  @RequestMapping(value = "/admin/delete-user/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> deleteUser(@PathVariable("userId") String userId) {
    try {
      return new ResponseEntity<UserModel>(userService.deleteUser(userId), HttpStatus.OK);
    } catch (UsernameNotFoundException error) {
      error.printStackTrace();
      return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  // TODO: mettere @PreAuthorize e controllare ruolo admin
  /**
   * 
   * @param jwtToken the token of the user
   * @return all the developers that are disabled.
   */
  @RequestMapping(value = "/users/developer/get-all-disabled", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserModel>> getAllDevelopmentToEnable(@RequestHeader("Authorization") String jwtToken) {
    String userId = ParseJWT.getIdFromJwt(jwtToken);
    ResponseEntity<List<UserModel>> mydevelopmentneedenable = new ResponseEntity<List<UserModel>>(
        userService.getAllDevelopmentToEnable(userId), HttpStatus.OK);
    return mydevelopmentneedenable;
  }

  /**
   * 
   * @param token the token of the user
   * @return all the user, exclude the admin, that are register in the system.
   */
  @RequestMapping(value = "/users/admin/get-all-user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserModel>> getAllUser(@RequestHeader("Authorization") String token) {
    return new ResponseEntity<List<UserModel>>(userService.getAllUsers(), HttpStatus.OK);
  }

  /**
   * @param user the user obj with username and password
   * @return ResponseEntity if the operation completed correctly otherwise return
   *         an error response.
   */
  @RequestMapping(value = "/users/sign-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> signUp(@RequestBody UserModel user) {
    if (userService.addUser(user).getId() != null) {
      return new ResponseEntity<UserModel>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  /**
   * @param token the JWT token from the header of the request
   * @return An Usermodel if the operation completed correctly otherwise return an
   *         unavailable response.
   */
  @RequestMapping(value = "/users/get-info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> getUserInfo(@RequestHeader("Authorization") String token) {
    try {
      return new ResponseEntity<UserModel>(userService.getUserInfo(ParseJWT.getIdFromJwt(token)), HttpStatus.OK);
    } catch (UsernameNotFoundException error) {
      error.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * @param token       Token of user
   * @param newUserData New data od user
   * @return All user info.
   */
  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/users/modify", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> usersModify(@RequestHeader("Authorization") String token,
      @RequestBody UserModel newUserData) {
    try {
      Optional<String> role = Optional.ofNullable(newUserData.getRole());
      if (role.isPresent() && role.get().equals("ROLE_TEACHER")) {
        exerciseService.modifyExerciseAuthorName(newUserData, token);
      }
      UserModel user = userService.updateUser(newUserData, token);
      return new ResponseEntity<UserModel>(user, HttpStatus.OK);
    } catch (UsernameNotFoundException error) {
      error.printStackTrace();
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getMessage(), error);
    } catch (NotOwnerException error) {
      error.printStackTrace();
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
  }

  /**
   * @param id the unique id of the user
   * @return true if the user has been activated, else return false.
   */
  @RequestMapping(value = "/users/activate-user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> activateUser(@PathVariable("id") String id) {
    userService.activateUser(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * @param exercise the exercise which needs to be inserted in the database
   * @return A new ResponseEntity that contains the phrase.
   */
  @RequestMapping(value = "/exercises/insert-exercise", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ExerciseModel> insertExercise(@RequestBody ExerciseHelper exercise) {
    try {
      ExerciseModel exerciseModel = exerciseService.insertExercise(exercise);
      userService.addExerciseItem(exercise.getAssignedUsersIds(), exerciseModel);
      return new ResponseEntity<ExerciseModel>(exerciseModel, HttpStatus.OK);
    } catch (Exception error) {
      error.printStackTrace();
      return new ResponseEntity<ExerciseModel>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * @param exercise the exercise which needs to be inserted in the database
   * @return A new ResponseEntity that contains the phrase.
   * @author Gionata Legrottaglie
   */
  @RequestMapping(value = "/exercises/student/insert-free-exercise", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ExerciseModel> insertFreeExercise(@RequestHeader("Authorization") String token,
      @RequestBody ExerciseHelper exercise) {
    try {
      ExerciseModel exerciseModel = exerciseService.insertFreeExercise(exercise, ParseJWT.getIdFromJwt(token));
      return new ResponseEntity<ExerciseModel>(exerciseModel, HttpStatus.OK);
    } catch (Exception error) {
      error.printStackTrace();
      return new ResponseEntity<ExerciseModel>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * 
   * @param token            the unique token of the user, in this case a student
   * @param correctionHelper contains the unique id of the exercise and the
   *                         solution that was written by the student
   * @return the teacher solution of the exercise.
   */
  @RequestMapping(value = "/exercises/student/do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SolutionModel> doExercise(@RequestHeader("Authorization") String token,
      @RequestBody CorrectionHelper correctionHelper) {
    try {
      SolutionModel insertedSolution = exerciseService.doExercise(correctionHelper, ParseJWT.getIdFromJwt(token));
      return new ResponseEntity<>(insertedSolution, HttpStatus.OK);
    } catch (Exception error) {
      error.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * 
   * @param stringObj    the text which has to be analyzed by freeling as map
   * @param studentToken the unique token of the user
   * @return A SolutionModel with the analyzed sentence or empty if the service is
   *         unavailable.
   */
  @RequestMapping(value = "/exercises/automatic-solution", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SolutionModel> getCorrection(@RequestBody Map<String, String> stringObj,
      @RequestHeader("Authorization") String studentToken) {
    try {
      SolutionModel solution = solutionService.getAutomaticCorrection(stringObj.get("text"));
      return new ResponseEntity<SolutionModel>(solution, HttpStatus.OK);
    } catch (IOException error) {
      error.printStackTrace();
      return new ResponseEntity<SolutionModel>(new SolutionModel(), HttpStatus.SERVICE_UNAVAILABLE);
    }
  }

  /**
   * @param token the unique of the student
   * @return all the exercises that the student has to do.
   */
  @RequestMapping(value = "/exercises/user-todo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Iterable<ExerciseModel>> getUserExercise(@RequestHeader("Authorization") String token) {
    try {
      List<String> exercisesToDoId = userService.findAllExerciseToDo(ParseJWT.getIdFromJwt(token));
      Iterable<ExerciseModel> exerciseToDo = exerciseService.getAllByIds(exercisesToDoId);
      return new ResponseEntity<>(exerciseToDo, HttpStatus.OK);
    } catch (Exception error) {
      error.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * @param token the token authorization
   * @return the list of the exercise done by the student.
   */
  @RequestMapping(value = "/exercises/done", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getExerciseDone(@RequestHeader("Authorization") String token) {
    List<String> exercisesDoneid = userService.getAllExerciseDone(ParseJWT.getIdFromJwt(token));
    Iterable<ExerciseModel> exercisesDone = exerciseService.getAllByIds(exercisesDoneid);
    return new ResponseEntity<>(exercisesDone, HttpStatus.OK);
  }

  /**
   * @return all the students that are present in the system.
   */
  @RequestMapping(value = "/users/get-students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserModel>> getStudentsList() {
    ResponseEntity<List<UserModel>> listResponseEntity = new ResponseEntity<List<UserModel>>(
        userService.getAllStudents(), HttpStatus.OK);
    return listResponseEntity;
  }

  /**
   * @param exerciseId the unique id of the exercise
   * @param jwtToken   the token of the theacher who is going to delete a exercise
   * @return ccc.
   * 
   *         @RequestMapping( value = "/exercises/delete/{exerciseid}", method =
   *         RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
   *         public ResponseEntity<Object>
   *         deleteExercise( @RequestParam("exerciseid") String
   *         exerciseId, @RequestHeader("Authorization") String jwtToken) { String
   *         userId = ParseJWT.getIdFromJwt(jwtToken);
   *         userService.deleteExerciseAssigned(exerciseId, userId);
   *         exerciseService.deleteExercise(exerciseId); return new
   *         ResponseEntity<>(HttpStatus.OK); }
   */
}
