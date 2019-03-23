package it.colletta.controller;

import it.colletta.model.Users;
import it.colletta.security.ParseJWT;
import it.colletta.service.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody Users user) {
        return userService.addUser(user).toString();

    }

    @RequestMapping(value = "/get-info", method = RequestMethod.GET)
    public Users getUserInfo(@RequestHeader("Authorization") String token) {
        Users user = new Users();
        user.setUsername(ParseJWT.parseJWT(token));
        return userService.getUserInfo(user);
    }

}
