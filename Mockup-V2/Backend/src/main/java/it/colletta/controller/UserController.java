package it.colletta.controller;

import it.colletta.model.Users;
import it.colletta.repository.UsersRepository;
import it.colletta.security.ParseJWT;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;
import java.awt.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UsersRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UsersRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(new Users(user.getUsername(), user.getPassword()));
        user.setPassword(null);
        return user.toString();
    }

    @RequestMapping(value = "/get-info", method = RequestMethod.GET)
    public String getUserInfo(@RequestHeader("Authorization") String token) {
       String username = ParseJWT.parseJWT(token);
       Users user = applicationUserRepository.findByUsername(username);
       //user.setPassword(null);
       return token + "                 " + username;
    }

}