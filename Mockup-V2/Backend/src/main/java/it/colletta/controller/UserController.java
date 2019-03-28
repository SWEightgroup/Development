package it.colletta.controller;

import it.colletta.model.UserModel;
import it.colletta.security.ParseJWT;
import it.colletta.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @param user the user obj with username and password
     * @return ResponseEntity if the operation completed correctly otherwise return an error
     * response
     */
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> signUp(@RequestBody UserModel user) {
        System.out.println(user.toString());
        if(userService.addUser(user).getId() != null) {
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        //return new ResponseEntity<UserModel>(userService.addUser(user), HttpStatus.OK);
    }

    /**
     * @param token the JWT token from the header of the request
     * @return An Usermodel if the operation completed correctly otherwise return an unavailable
     * response
     */
    @RequestMapping(value = "/get-info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @RequestMapping(value = "/activate-user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> activateUser(@PathVariable("id") String id) {
        userService.activateUser(id);
        //TODO BETTER
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param
     * @return
     */
    @RequestMapping(value = "/users/modify/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> usersModify(@RequestParam String username, @RequestParam String firstName,
                                                 @RequestParam String lastName, @RequestParam String dataOfBirth,
                                                 @RequestParam String role,     @RequestParam String language,
                                                 @PathVariable("id") String id) {
        UserModel user = new UserModel();
        user = userService.updateUser(username, firstName, lastName, dataOfBirth, role, language );

        if(user.getId() != null) {
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

}
