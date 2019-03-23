package it.colletta.controller;

import it.colletta.model.Users;
import it.colletta.repository.UsersRepository;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @PostMapping("/sign-up")
    public String signUp(@RequestBody Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(new Users(user.getUsername(), user.getPassword()));
        user.setPassword(null);
        return user.toString();
    }

    @RequestMapping(value = "/get-info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Users getUserInfo(@RequestParam("username") String username) {
       Users user = applicationUserRepository.findByUsername(username);
       user.setPassword(null);
       return user;
    }

}