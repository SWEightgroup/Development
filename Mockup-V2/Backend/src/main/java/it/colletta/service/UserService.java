package it.colletta.service;

import it.colletta.model.UserModel;
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

    public UserModel addUser(UserModel user) {
        final String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        applicationUserRepository.save(user);
        user.setPassword(null);
        return user;
    }

    public UserModel getUserInfo(UserModel user) {
        return applicationUserRepository.findByEmail(user.getEmail());
    }

    public void activateUser(String id) {
        applicationUserRepository.updateActivateFlagOnly(id);
    }
}
