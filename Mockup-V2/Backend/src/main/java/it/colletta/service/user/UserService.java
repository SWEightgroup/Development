package it.colletta.service.user;

import com.mongodb.BasicDBObject;
import it.colletta.model.ExerciseModel;
import it.colletta.model.SignupRequestModel;
import it.colletta.model.UserModel;
import it.colletta.repository.user.UsersRepository;
import it.colletta.security.ParseJWT;
import it.colletta.security.Role;
import it.colletta.service.signup.SignupRequestService;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.acl.NotOwnerException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        /**
         * TODO FARE CHECK SUL RUOLO E SETTARE FALSE SOLO SE SVILUPPATORE
         */
        user.setEnabled(true);
        user = applicationUserRepository.save(user);
        SignupRequestModel signupRequestModel = SignupRequestModel.builder()
            .userReference(user.getId())
            .requestDate(Calendar.getInstance().getTime())
            .build();
        user.setPassword(null);
        return user;
    }

    public Optional<UserModel> findById(String userId) {
        return applicationUserRepository.findById(userId);
    }

    public UserModel getUserInfo(String id) {
        Optional<UserModel> userModelOptional =
                applicationUserRepository.findById(id);
        if(userModelOptional.isPresent()) {
            return userModelOptional.get();
        }
        else {
            throw new UsernameNotFoundException("Id not refer to a user of the sistem");
        }
    }

    public void activateUser(String id) {
        applicationUserRepository.updateActivateFlagOnly(id);
    }

    public UserModel findByEmail(String email){ return applicationUserRepository.findByEmail(email);}

    public UserModel updateUser(UserModel newUserData, String token) throws NotOwnerException{

    	String email = ParseJWT.parseJWT(token);
        String newEmail =newUserData.getUsername();
        if(!email.equals(newEmail) && applicationUserRepository.findByEmail(newEmail) != null ) //ho modificato la mia mail
        	throw new NotOwnerException();
        	UserModel user = applicationUserRepository.findByEmail(email);
    		return applicationUserRepository.updateUser(user,newUserData);
    }

    public void addExerciseItem(List<String> assignedUsersIds, ExerciseModel exerciseModel) {
        Iterable<UserModel> users = applicationUserRepository.findAllById(assignedUsersIds);
        for(UserModel user : users) {
            user.addExerciseToDo(exerciseModel); //TODO se un esercizio ritorna false lancio eccezione
        }
        applicationUserRepository.saveAll(users);
    }

    public List<ExerciseModel> findAllExerciseToDo(String userId) {
        Optional<UserModel> userModel = applicationUserRepository.findById(userId);
        if(userModel.isPresent()) {
            return userModel.get().getExercisesToDo();
        }
        else {
            throw new UsernameNotFoundException("User not found in the system");
        }
    }

    public List<UserModel> getAllStudents() {
        return applicationUserRepository.findAllStudents();
    }

    public Optional<UserModel> deleteExerciseAssigned(ExerciseModel exercise, String userId) {
        Optional<UserModel> user = applicationUserRepository.findById(userId);
        if(user.isPresent()) {
            if(exercise.getAuthorId().equals(user.get().getId()) && user.get().getRole().equals(Role.TEACHER)) {
                applicationUserRepository.deleteFromExerciseToDo(exercise.getId());
            }
        }
        return Optional.empty();
    }
}
