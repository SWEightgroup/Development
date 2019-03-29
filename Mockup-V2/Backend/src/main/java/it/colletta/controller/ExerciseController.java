package it.colletta.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import it.colletta.model.*;
import it.colletta.model.helper.InsertExerciseModel;
import it.colletta.service.classes.ClassService;
import it.colletta.service.user.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ExecutableRemoveOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.colletta.service.ExerciseService;
import it.colletta.service.PhraseService;
import it.colletta.service.SolutionService;


@RestController
@RequestMapping("/exercises")
public class ExerciseController {

  @Autowired
  private ExerciseService exerciseService;

  @Autowired
  private PhraseService phraseService;

  @Autowired
  private SolutionService solutionService;

  @Autowired
  private ClassService classService;



    /**
     * @param phrase the text which needs to be inserted in the database
     * @return A new ResponseEntity that contains the phrase
     */
    @RequestMapping(value = "/insert-exercise", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExerciseModel> insertExercise(@RequestBody InsertExerciseModel exercise) {
        try {
            exerciseService.insertExercise(exercise);
            return new ResponseEntity<ExerciseModel>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ExerciseModel>(HttpStatus.BAD_REQUEST);
        }
    }
  /**
   * @param text the text which has to be analyzed by freeling
   * @return A CorrectionModel with the analyzed sentence or empty if the service is unavailable
   */
  @RequestMapping(value = "/automatic-solution", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SolutionModel> getCorrection(@RequestBody String text) {
    try {
      return new ResponseEntity<SolutionModel>(solutionService.getAutomaticCorrection(text),
          HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<SolutionModel>(new SolutionModel(), HttpStatus.SERVICE_UNAVAILABLE);
    }
  }


}
