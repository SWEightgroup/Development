/**
 * @path it.colletta.controller.ClassController
 * @author Francesco Magarotto, Enrico Muraro, Francesco Corti
 * @date 2019-03-23
 * @description Manage the HTTP teacher request regarding the classes
 */

package it.colletta.controller;

import it.colletta.model.ClassModel;
import it.colletta.model.helper.ClassHelper;
import it.colletta.model.helper.StudentClassHelper;
import it.colletta.model.validator.ClassHelperValidator;
import it.colletta.model.validator.StudentClassHelperValidator;
import it.colletta.security.ParseJwt;
import it.colletta.service.ExerciseService;
import it.colletta.service.classes.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

  @InitBinder("classHelper")
  protected void initBinderClass(WebDataBinder binder){
    binder.setValidator(new ClassHelperValidator());
  }

  @InitBinder("studentClassHelper")
  protected void initBinderStudent(WebDataBinder binder){
    binder.setValidator(new StudentClassHelperValidator());
  }

  private ClassService classService;

  @Autowired
  public ClassController(ClassService classService) {
    this.classService = classService;
  }

  /**
   * @param classHelper the new class which needs to be inserted in the database
   * @param token the authorization token of the teacher
   * @return A new ResponseEntity that contains the status of the operation.
   */
  @RequestMapping(
          value = "/create",
          method = RequestMethod.POST,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HttpStatus> createClass(@RequestHeader("Authorization") String token,
                                                @Valid @RequestBody ClassHelper classHelper) {
    try{
        String newClassName = classService.createNewClass(classHelper, ParseJwt.getIdFromJwt(token));
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * @param studentClassHelper DTO with unique classId and List<String> userId
   * @param token the authorization token of the teacher
   * @return A new ResponseEntity that contains the student that was added to the class.
   */
  @RequestMapping(
          value = "/modify",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HttpStatus> modifyStudentsClass(@RequestHeader("Authorization") String token,
                                                    @Valid @RequestBody StudentClassHelper studentClassHelper) {
    try{
        classService.modifyExistingStudentClass(studentClassHelper);
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * @param classHelper the class that is going to change the name
   * @param token the authorization token of the teacher
   * @return A new ResponseEntity that contains the new name of the class
   */
  @RequestMapping(
          value = "/rename",
          method = RequestMethod.PUT,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> renameClass(@RequestHeader("Authorization") String token,
                                            @Valid @RequestBody ClassHelper classHelper) {
    try{
        String newClassName = classService.renameExistingClass(classHelper.getClassId(),classHelper.getName());
        return new ResponseEntity<String>(newClassName, HttpStatus.OK);
    } catch (Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * @param classId the unique Id of the class
   * @param token the authorization token of the teacher
   * @return A new ResponseEntity that contains the status of the operation
   */
  @RequestMapping(
          value = "/{classId}",
          method = RequestMethod.DELETE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HttpStatus> deleteClass(@RequestHeader("Authorization") String token,
                                                @PathVariable("classId") String classId) {
    try{
      classService.deleteClass(classId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e){
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * @param token the authorization token of the teacher
   * @return A new ResponseEntity that contains the list of teacher classes with all the fields
   */
  @RequestMapping(
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ClassModel>> getAllClasses(@RequestHeader("Authorization") String token) {
    try{
      return new ResponseEntity<List<ClassModel>>(classService.getAllClasses(ParseJwt.getIdFromJwt(token)), HttpStatus.OK);
    } catch (Exception e){
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
