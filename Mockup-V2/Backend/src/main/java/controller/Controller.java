package controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import resources.LoginModel;
import resources.RegistrationModel;
import resources.SentenceModel;
import service.FirebaseAuthInterface;
import service.SentenceService;
import service.UsersService;

@CrossOrigin
@RestController
@RequestMapping("/sw")
public class Controller {

  @Autowired private SentenceService sentenceService;
  @Autowired private FirebaseAuthInterface auth;

  /**
   * if you pass a string containing a phrase with an ending point, you will receive a grammatical
   * analysis of the phrase
   *
   * @param phrase
   * @return
   */
  @RequestMapping(value = "/s", method = RequestMethod.POST, produces = "application/json" ,consumes = "application/json" )
  public String wsGetSolution(@RequestBody SentenceModel sentence) {
    try {    	
      return sentenceService.getSolution(sentence.text).trim();
    } catch (IOException e) {
      e.printStackTrace();
      return new String();
    }
  }
  
  
  /**
   * Return token and user information.
   * 
   * @param token
   * @return User information
   * @throws Exception
   */
  @RequestMapping  (value = "/login", method = RequestMethod.POST , produces = "application/json" ,consumes = "application/json" )
  public Map<String, Object> wsLogin(@RequestBody LoginModel login) throws Exception {
    try {
    	return  UsersService.login(login,auth);
    	
    } catch (Exception error) {
      error.printStackTrace();
      throw new Exception("Access denied");
    }
  }
  
  /**
   * 
   * Create a new user.
   * 
   * @param newUser
   * @return A token referring to the new user
   * @throws Exception
   */
  @RequestMapping  (value = "/nu", method = RequestMethod.POST , produces = "application/json" ,consumes = "application/json" )
  public Map<String, Object>  wsNewUser(@RequestBody RegistrationModel newUser) throws Exception {
    try {
    	return  UsersService.newUser(newUser,auth);
    } catch (Exception error) {
      error.printStackTrace();
      throw new Exception("New user creation failed");
    }
  }
 

}
