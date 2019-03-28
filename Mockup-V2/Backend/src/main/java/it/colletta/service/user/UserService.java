package it.colletta.service.user;

import it.colletta.model.SignupRequestModel;
import it.colletta.model.UserModel;
import it.colletta.repository.user.UsersRepository;
import it.colletta.service.signup.SignupRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UsersRepository applicationUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel addUser(UserModel user) {
        SignupRequestService signupRequestService = new SignupRequestService();
        final String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setActivated(false);
        user = applicationUserRepository.save(user);
        SignupRequestModel signupRequestModel = SignupRequestModel.builder()
                .userReference(user.getId())
                .requestDate(Calendar.getInstance().getTime())
                .build();
        user.setPassword(null);
        return user;
    }
    public UserModel getUserInfo(UserModel user) {
        return applicationUserRepository.findByEmail(user.getUsername());
    }

    public void activateUser(String id) {
        applicationUserRepository.updateActivateFlagOnly(id);
    }

    public UserModel updateUser(UserModel userModel){

        UserModel user = applicationUserRepository.findByEmail(userModel.getUsername());
            if(user.getId() userModel.getId()) {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setDateOfBirth(new Date()); //TODO Gestire meglio sta cosa delle date meglio usare un long che è fecile da convertire in timestamp
                user.setRole(role);
                user.setLanguage(language);
            }
            //gestire meglio i controlli
        return applicationUserRepository.save(user);
    }
}
