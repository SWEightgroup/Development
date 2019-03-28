package it.colletta.controller;

import java.io.IOException;
import java.util.List;

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
   * @param exercise the InsertEcerciseModel with all the parameters
   * @return A new ResponseEntity that contains the phrase
   */
  @RequestMapping(value = "/insert-exercise", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ExerciseModel> insertExercise(@RequestBody InsertExerciseModel exercise){

      PhraseModel exercisePhrase = phraseService.insertPhrase(exercise.getPhrase());
      String exerciseId = exerciseService.insertExercise(exercise.getVisibility(), exercise.getTeacherId(), exercisePhrase.getId());

      //TODO: aggiungere all'insegnante l'Id dell'esercizio che stiamo assegnando nel campo "exercises" CRUD: addExerciseId(exerciseId)
    
      if(exercise.getClassId().size() != 0){   // assign the exercise in one or more classes
          Iterable<ClassModel> classes = classService.findAllClasses(exercise.getClassId());
          exerciseService.assignExerciseToClasses(classes, exerciseId);
      }
      else{   // assign the exercise in one or more students
          //TODO: caso in cui ho una lista di studenti e non di classi
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
