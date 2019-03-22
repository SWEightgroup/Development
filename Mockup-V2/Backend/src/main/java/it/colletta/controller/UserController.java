package it.colletta.controller;

import it.colletta.model.Users;
import it.colletta.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public String getInfo(String id) {
        return applicationUserRepository.findByUsername(id).toString();

    }
    @PostMapping("/sign-up")
    public Object signUp(@RequestBody Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(new Users(user.getUsername(), user.getPassword()));
        return user.toString();
    }
}