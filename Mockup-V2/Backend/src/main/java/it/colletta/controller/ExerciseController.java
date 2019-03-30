package it.colletta.controller;

import java.io.IOException;
import java.util.List;

import it.colletta.model.helper.ExerciseHelper;
import it.colletta.repository.exercise.ExerciseRepository;
import it.colletta.security.ParseJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.colletta.model.ExerciseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;
import it.colletta.service.ExerciseService;
import it.colletta.service.SolutionService;


@RestController
@RequestMapping("/exercises")
public class ExerciseController {

  @Autowired
  private ExerciseService exerciseService;

  @Autowired 
  ExerciseRepository exerciseRepository;

  @Autowired
  private SolutionService solutionService;



    /**
     * @param exercise the exercise which needs to be inserted in the database
     * @return A new ResponseEntity that contains the phrase
     */
  @RequestMapping(value = "/insert-exercise", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ExerciseModel> insertExercise(@RequestBody ExerciseHelper exercise) {
        try {
          // 1) inserire la frase nel sistema 
          ExerciseModel model = exerciseService.insertExercise(exercise);
          //exerciseService.insertExercise(exercise);
          return new ResponseEntity<ExerciseModel>(model, HttpStatus.OK);
        }catch (Exception e) {
          return new ResponseEntity<ExerciseModel>(HttpStatus.BAD_REQUEST);
        }
    }


  @RequestMapping(value="/get-public-exercises", method=RequestMethod.GET)
  public List<Object> getPublicExercises(@RequestHeader("Authorization") String studentToken) {
      String userId = ParseJWT.parseJWT(studentToken);
      //exerciseService.getPublicExercises(userId);
      return null;
  }
  
  
  /**
   * @param text the text which has to be analyzed by freeling
   * @return A CorrectionModel with the analyzed sentence or empty if the service
   *         is unavailable
   */
  @RequestMapping(value = "/automatic-solution", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SolutionModel> getCorrection(@RequestBody String text) {
    try {
      return new ResponseEntity<SolutionModel>(solutionService.getAutomaticCorrection(text), HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<SolutionModel>(new SolutionModel(), HttpStatus.SERVICE_UNAVAILABLE);
    }
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Iterable<ExerciseModel>> getUserExercise(@PathVariable("userId") String userId) {
    try {
      return new ResponseEntity<>(null, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Iterable<ExerciseModel>>(HttpStatus.BAD_REQUEST);
    }
  }
}