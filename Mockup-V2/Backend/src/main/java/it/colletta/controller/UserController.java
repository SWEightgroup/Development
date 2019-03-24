package it.colletta.controller;

import it.colletta.model.UserModel;
import it.colletta.security.ParseJWT;
import it.colletta.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody UserModel user) {
        return userService.addUser(user).toString();

    }

    @RequestMapping(value = "/get-info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel getUserInfo(@RequestHeader("Authorization") String token) {
        UserModel user = new UserModel();
        user.setEmail(ParseJWT.parseJWT(token));
        return userService.getUserInfo(user);
    }

    @RequestMapping(value = "/activate-user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object activateUser(@PathVariable("id") String id) {
        userService.activateUser(id);
        return null;
    }
}
