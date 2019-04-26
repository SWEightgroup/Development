/*
  Menage all request regarding the exercises.
  @path it.colletta.controller.ExerciseController
  @author Francesco Magarotto, Enrico Muraro, Francesco Corti
  @date 2019-03-27
  @description Menage the HTTP user request regarding the exercises
*/

package it.colletta.controller;

import it.colletta.controller.assembler.ExerciseResourceAssembler;
import it.colletta.model.ExerciseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.helper.CorrectionHelper;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.security.ParseJwt;
import it.colletta.service.ExerciseService;
import it.colletta.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private ExerciseService exerciseService;
    private SolutionService solutionService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService,
                              SolutionService solutionService) {
        this.exerciseService = exerciseService;
        this.solutionService = solutionService;
    }

    /**
     * Return all exercises done by a student.
     *
     * @param token     JWT token contained in the header request
     * @param pageable  {@link Pageable}
     * @param assembler {@link org.springframework.hateoas.ResourceAssembler}
     * @return List of exercises inserted by the user
     */
    @RequestMapping(value = "/done", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allExercisesDone(@RequestHeader("Authorization") String token,
                                              @PageableDefault(value = 4) Pageable pageable,
                                              PagedResourcesAssembler<ExerciseModel> assembler) {
        String id = ParseJwt.getIdFromJwt(token);

        Page<ExerciseModel> exercisesDone = exerciseService.getAllDoneBySudentId(pageable, id);
        PagedResources<?> resources = assembler
                .toResource(exercisesDone, new ExerciseResourceAssembler("/done-alt"));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    /**
     * Returns all the exercises added by the requesting teacher.
     *
     * @param token     JWT token contained in the header request
     * @param pageable  pageable
     * @param assembler assembler
     * @return List of exercises inserted by the user
     */
    @RequestMapping(value = "/added", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allAddedExercises(@RequestHeader("Authorization") String token,
                                               @PageableDefault(value = 4) Pageable pageable,
                                               PagedResourcesAssembler<ExerciseModel> assembler) {
        String id = ParseJwt.getIdFromJwt(token);

        Page<ExerciseModel> exercisesDone = exerciseService.getAllAddedByTeacherId(pageable, id);
        PagedResources<?> resources = assembler
                .toResource(exercisesDone, new ExerciseResourceAssembler("/added-alt"));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    /**
     * Return all exercises to by using auth token authentication.
     *
     * @param token     JWT token contained in the header request
     * @param pageable  {@link Pageable}
     * @param assembler {@link org.springframework.hateoas.ResourceAssembler}
     * @return A paged version of the exercises to do
     * @see com.auth0.jwt.JWT
     */
    @RequestMapping(value = "/todo", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allExercisesToDo(@RequestHeader("Authorization") String token,
                                              @PageableDefault(value = 4) Pageable pageable,
                                              PagedResourcesAssembler<ExerciseModel> assembler) {
        String id = ParseJwt.getIdFromJwt(token);
        Page<ExerciseModel> exercisesToDo = exerciseService.getAllToDoByStudentId(pageable, id);
        PagedResources<?> resources = assembler
                .toResource(exercisesToDo, new ExerciseResourceAssembler("/todo-alt"));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    /**
     * Insert an new exercise.
     *
     * @param exercise the exercise which needs to be inserted in the database
     * @return the inserted phrase.
     */

    @RequestMapping(value = "/insert-exercise", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExerciseModel> insertExercise(@RequestBody ExerciseHelper exercise) {
        try {
            ExerciseModel exerciseModel = exerciseService.insertExercise(exercise);

            return new ResponseEntity<>(exerciseModel, HttpStatus.OK);
        } catch (Exception error) {
            error.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Insert a new solution of the free exercise done by a student.
     *
     * @param token    JWT token contained in the header request
     * @param exercise the free exercise which needs to be inserted in the database
     * @return the inserted phrase
     */
    @RequestMapping(value = "/student/insert-free-exercise", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExerciseModel> insertFreeExercise(
            @RequestHeader("Authorization") String token,
            @RequestBody ExerciseHelper exercise) {
        try {
            ExerciseModel exerciseModel;
            exerciseModel = exerciseService
                    .insertFreeExercise(exercise, ParseJwt.getIdFromJwt(token));
            return new ResponseEntity<>(exerciseModel, HttpStatus.OK);
        } catch (Exception error) {
            error.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Do the correction of the exercise and then give a mark.
     *
     * @param token            the unique token of the user, in this case a student
     * @param correctionHelper <p>contains the unique id of the exercise and the solution that was
     *                         written by the student</p>
     * @return the teacher solution of the exercise.
     */
    @RequestMapping(value = "/student/do", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SolutionModel> doExercise(@RequestHeader("Authorization") String token,
                                                    @RequestBody CorrectionHelper correctionHelper) {
        try {
            SolutionModel insertedSolution = exerciseService
                    .doExercise(correctionHelper, ParseJwt.getIdFromJwt(token));
            return new ResponseEntity<>(insertedSolution, HttpStatus.OK);
        } catch (Exception error) {
            error.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Return the automatic solution of a phrase provided by Freeling library.
     *
     * @param stringObj    the text which has to be analyzed by Freeling as map
     * @param studentToken the unique token of the user
     * @return A SolutionModel with the analyzed sentence or empty if the service is unavailable.
     */
    @RequestMapping(value = "/automatic-solution", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SolutionModel> getCorrection(@RequestBody Map<String, String> stringObj,
                                                       @RequestHeader("Authorization") String studentToken) {
        try {
            SolutionModel solution = solutionService.getAutomaticCorrection(stringObj.get("text"));
            return new ResponseEntity<SolutionModel>(solution, HttpStatus.OK);
        } catch (IOException error) {
            error.printStackTrace();
            return new ResponseEntity<SolutionModel>(new SolutionModel(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    /**
     * Return all public exercises to do by using auth token authentication.
     *
     * @param studentToken JWT token contained in the header request
     * @param pageable     {@link Pageable}
     * @param assembler    {@link org.springframework.hateoas.ResourceAssembler}
     * @return A paged version of the exercises public to do
     * @see com.auth0.jwt.JWT
     */
    @RequestMapping(value = "/public", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPublicExercises(@PageableDefault(value = 4) Pageable pageable,
                                                PagedResourcesAssembler<ExerciseModel> assembler,
                                                @RequestHeader("Authorization") String studentToken) {
        try {
            Page<ExerciseModel> exercisesToDo = exerciseService
                    .getAllPublicExercises(pageable, ParseJwt.getIdFromJwt(studentToken));
            PagedResources<?> resources = assembler
                    .toResource(exercisesToDo, new ExerciseResourceAssembler("/public-exercise"));
            return new ResponseEntity<>(resources, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/{exerciseId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteExercise(@RequestParam("exerciseId") String exerciseId,
                                            @RequestHeader("Authorization") String teacherToken) {
        try {
            return new ResponseEntity<>(
                    exerciseService.deleteExercise(exerciseId, ParseJwt.getIdFromJwt(teacherToken)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
