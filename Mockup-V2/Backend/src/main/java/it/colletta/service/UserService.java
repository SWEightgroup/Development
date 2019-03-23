package it.colletta.service;

import it.colletta.model.Users;
import it.colletta.repository.UsersRepository;
import it.colletta.security.ParseJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository applicationUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Users addUser(Users user) {
        bCryptPasswordEncoder.encode(user.getPassword());
        applicationUserRepository.save(new Users(user.getUsername(), user.getPassword()));
        user.setPassword(null);
        return user;
    }

    public Users getUserInfo(Users user) {
        return applicationUserRepository.findByUsername(user.getUsername());
    }
}
