package it.colletta.controller;

import it.colletta.model.StudentModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.FavoriteTeacherHelper;
import it.colletta.security.ParseJwt;
import it.colletta.service.user.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

  private UserService userService;

  @Autowired
  public StudentController(UserService userService) {
    this.userService = userService;
  }

  /**
   * @param token the unique token of the user
   * @return the update List of student favorite teacher/s.
   */
  @PreAuthorize("hasRole('STUDENT')")
  @RequestMapping(
          value = "/favorite-teacher",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HttpStatus> modifyFavoriteTeachers(
          @RequestHeader("Authorization") String token,
          @RequestBody FavoriteTeacherHelper favoriteTeacherHelper) {
    try{
      userService.modifyFavoriteTeachers(ParseJwt.getIdFromJwt(token), favoriteTeacherHelper.getTeacherId());
      return new ResponseEntity<>(HttpStatus.OK);
    } catch(Exception e){
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * @param token the unique token of the user
   * @return the actual List of student favorite teacher/s.
   */
  @PreAuthorize("hasRole('STUDENT')")
  @RequestMapping(
          value = "/favorite-teacher",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserModel>> getFavoriteTeachers(
          @RequestHeader("Authorization") String token) {
    try {
      return new ResponseEntity<>(
              userService.getFavoriteTeachers(ParseJwt.getIdFromJwt(token)),
              HttpStatus.OK);
    } catch(Exception e){
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
