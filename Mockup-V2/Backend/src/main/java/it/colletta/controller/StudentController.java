package it.colletta.controller;

//import com.sun.javaws.progress.Progress;
import it.colletta.model.StudentModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.FavoriteTeacherHelper;
import it.colletta.model.helper.ProgressHelper;
import it.colletta.security.ParseJwt;
import it.colletta.service.student.StudentService;
import it.colletta.service.user.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

  private StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  /**
   * @param token the unique token of the user
   * @return HttpStatus of the operation.
   */
  @RequestMapping(
      
          value = "/favorite-teacher/{teacherId}",
          method = RequestMethod.POST,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HttpStatus> addTeacher(
          @RequestHeader("Authorization") String token,
          @PathVariable("teacherId") String teacherId) {
    try{
      studentService.addFavoriteTeacher(ParseJwt.getIdFromJwt(token), teacherId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch(Exception e){
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * @param token the unique token of the user
   * @return HttpStatus of the operation.
   */
  @RequestMapping(
          value = "/favorite-teacher/{teacherId}",
          method = RequestMethod.DELETE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HttpStatus> deleteTeacher(
          @RequestHeader("Authorization") String token,
          @PathVariable("teacherId") String teacherId) {
    try{
      studentService.deleteFavoriteTeacher(ParseJwt.getIdFromJwt(token), teacherId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch(Exception e){
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * @param token the unique token of the user
   * @return HttpStatus of the operation
   */
  @RequestMapping(
          value = "/favorite-teacher",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HttpStatus> modifyFavoriteTeachers(
          @RequestHeader("Authorization") String token,
          @RequestBody FavoriteTeacherHelper favoriteTeacherHelper) {
    try{
      studentService.modifyFavoriteTeachers(ParseJwt.getIdFromJwt(token), favoriteTeacherHelper.getTeacherId());
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
  @RequestMapping(
          value = "/favorite-teacher",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserModel>> getFavoriteTeachers(
          @RequestHeader("Authorization") String token) {
    try {
      return new ResponseEntity<>(
              studentService.getFavoriteTeachers(ParseJwt.getIdFromJwt(token)),
              HttpStatus.OK);
    } catch(Exception e){
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(
      value = "/progress",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> progress(
      @RequestHeader("Authorization") String token) {
    try {
      return new ResponseEntity<>(
          studentService.getStudentProgress(ParseJwt.getIdFromJwt(token)),
          HttpStatus.OK);
    } catch(Exception e){
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }


}
