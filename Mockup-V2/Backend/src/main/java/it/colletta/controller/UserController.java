/*
 * @path it.colletta.controller.UserController
 * 
 * @author Francesco Magarotto, Francesco Corti
 * 
 * @date 2019-03-25
 * 
 * @description Menage the HTTP user request regarding the user
 * 
 */
package it.colletta.controller;

import it.colletta.model.UserModel;
import it.colletta.security.ParseJwt;
import it.colletta.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


  private UserService userService;

  @Autowired
  public void setUserService(UserService userService){
    this.userService = userService;
  }

  /**
   * @param userId the user unique id
   * @return the user deleted by the admin.
   */
  @RequestMapping(value = "/admin/delete-user/{userId}", method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> deleteUser(@PathVariable("userId") String userId) {
    try {
      return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    } catch (UsernameNotFoundException error) {
      error.printStackTrace();
      return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }


  /**
   * @param jwtToken the token of the user
   * @return all the developers that are disabled.
   */
  @RequestMapping(value = "/users/developer/get-all-disabled", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserModel>> getAllDevelopmentToEnable(
      @RequestHeader("Authorization") String jwtToken) {
    String userId = ParseJwt.getIdFromJwt(jwtToken);
    ResponseEntity<List<UserModel>> mydevelopmentneedenable =
        new ResponseEntity<>(userService.getAllDevelopmentToEnable(userId), HttpStatus.OK);
    return mydevelopmentneedenable;
  }

  /**
   * @param token the token of the user
   * @return all the user, exclude the admin, that are register in the system.
   */
  @RequestMapping(value = "/users/admin/get-all-user", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserModel>> getAllUser(@RequestHeader("Authorization") String token) {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  /**
   * @param user the user obj with username and password
   * @return ResponseEntity if the operation completed correctly otherwise return an error response.
   */
  @RequestMapping(value = "/users/sign-up", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> signUp(@RequestBody UserModel user) {
    if (userService.addUser(user).getId() != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  /**
   * @param token the JWT token from the header of the request
   * @return An Usermodel if the operation completed correctly otherwise return an unavailable
   *         response.
   */
  @RequestMapping(value = "/users/get-info", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> getUserInfo(@RequestHeader("Authorization") String token) {
    try {
      return new ResponseEntity<>(userService.getUserInfo(ParseJwt.getIdFromJwt(token)),
          HttpStatus.OK);
    } catch (UsernameNotFoundException error) {
      error.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * @param token Token of user
   * @param newUserData New data od user
   * @return All user info.
   */
  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/users/modify", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserModel> usersModify(@RequestHeader("Authorization") String token,
      @RequestBody UserModel newUserData) {
    try {
      Optional<String> role = Optional.ofNullable(newUserData.getRole()); // Io userei strategy
      /*
       * if (role.isPresent() && role.get().equals("ROLE_TEACHER")) {
       * exerciseService.modifyExerciseAuthorName(newUserData, token); }
       */
      // TODO trovare soluzione alternativa, non va nel controller
      UserModel user = userService.updateUser(newUserData, token);
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (UsernameNotFoundException error) {
      error.printStackTrace();
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getMessage(), error);
    }
  }

  /**
   * @param id the unique id of the user
   * @return true if the user has been activated, else return false.
   */
  @RequestMapping(value = "/users/activate-user/{id}", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> activateUser(@PathVariable("id") String id) {
    userService.activateUser(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /** @return all the students that are present in the system. */
  @RequestMapping(value = "/users/get-students", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserModel>> getStudentsList() {
    return new ResponseEntity<List<UserModel>>(userService.getAllStudents(), HttpStatus.OK);
  }
}
