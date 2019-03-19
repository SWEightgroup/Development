package it.colletta.service;

import it.colletta.repository.UserRepository;
import it.colletta.resources.RegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UsersService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private FirebaseAuthImplementation auth;

  /**
   * Call a firebase service to request a token and verify credentials.
   * 
   * @param email User email.
   * @param password User password.
   * @return Map contains token, uid and user information.
   * @throws Exception Exception.
   */
  public  Map<String, Object> login(String email, String password) 
    throws Exception {
    try {
      String token = auth.getToken(email, password);
      String uid = auth.getUid(token);
      return userRepository.getUserInformation(uid,token);
    } catch (Exception error) {
      error.printStackTrace();
      throw error;
    }

  }

  /**
   * Creates a new User in Firebase and add his information in Users Collection.
   * 
   * @param newUser Object contains the users information.
   * @return Map contains token, uid and user information.
   * @throws Exception Exception.
   */
  public  Map<String, Object> newUser(RegistrationModel newUser)
      throws Exception {
    try {
      String uid = auth.createUser(newUser.getEmail(), newUser.getPassword());
      Map<String, Object> user = userRepository.createNewUser(newUser,uid);
      user.put("token",  auth.createToken(uid));
      return user;

    } catch (Exception error) {
      error.printStackTrace();
      throw error;
    }
  }
}
