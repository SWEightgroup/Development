package it.colletta.controller;

import com.mongodb.util.JSON;
import java.io.IOException;
import java.security.acl.NotOwnerException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.colletta.model.ExerciseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.security.ParseJWT;
import it.colletta.service.ExerciseService;
import it.colletta.service.SolutionService;
import it.colletta.service.user.UserService;


/**
 *
 */
@RestController
public class Controller {

    @Autowired
    UserService userService;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    @Lazy
    SolutionService solutionService;

    /**
     * @param user the user obj with username and password
     * @return ResponseEntity if the operation completed correctly otherwise return an error
     * response
     */
    @RequestMapping(value = "/users/sign-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> signUp(@RequestBody UserModel user) {
        System.out.println(user.toString());
        if(userService.addUser(user).getId() != null) {
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * @param token the JWT token from the header of the request
     * @return An Usermodel if the operation completed correctly otherwise return an unavailable
     * response
     */
    @RequestMapping(value = "/users/get-info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> getUserInfo(@RequestHeader("Authorization") String token) {
        UserModel user = new UserModel();
        user.setEmail(ParseJWT.parseJWT(token));
        user = userService.getUserInfo(user);
        if(user.getId() != null) {
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    /**
     * @param id the id of the user
     * @return something
     */
    @RequestMapping(value = "/users/activate-user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> activateUser(@PathVariable("id") String id) {
        userService.activateUser(id);
        //TODO BETTER
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param
     * @return
     */
    @RequestMapping(value = "/users/modify", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> usersModify(@RequestHeader("Authorization") String token, @RequestBody UserModel newUserData) {
        try {
            Optional<String> role = Optional.ofNullable(newUserData.getRole());
            if(role.isPresent() && role.get().equals("ROLE_TEACHER")){
                exerciseService.modifyExerciseName(newUserData,token);
            }
            UserModel user = userService.updateUser(newUserData,token);
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        }
        catch(UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        catch(NotOwnerException n){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * @param exercise the exercise which needs to be inserted in the database
     * @return A new ResponseEntity that contains the phrase
     */
    @RequestMapping(value = "/exercises/insert-exercise", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExerciseModel> insertExercise(@RequestBody ExerciseHelper exercise) {
        try {
            ExerciseModel exerciseModel = exerciseService.insertExercise(exercise);
            userService.addExerciseItem(exercise.getAssignedUsersIds(), exerciseModel);
            return new ResponseEntity<ExerciseModel>(exerciseModel, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<ExerciseModel>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value="/exercises/get-public-exercises", method=RequestMethod.GET)
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
    @RequestMapping(value = "/exercises/automatic-solution", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SolutionModel> getCorrection(@RequestBody Map<String, String> stringObj, @RequestHeader("Authorization") String studentToken) {
        try {
        	String userId = ParseJWT.parseJWT(studentToken);
        	System.out.println("Frase in " + stringObj.get("text"));
        	SolutionModel solution = solutionService.getAutomaticCorrection(stringObj.get("text"));
        	System.out.println("Frase out " + solution.getSolutionText());
            return new ResponseEntity<SolutionModel>(solution, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<SolutionModel>(new SolutionModel(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/users/todo/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ExerciseModel>> getUserExercise(@PathVariable("userId") String userId) {
        try {
            List<ExerciseModel> exerciseToDo = userService.findAllExerciseToDo(userId);
            return new ResponseEntity<>(exerciseToDo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
