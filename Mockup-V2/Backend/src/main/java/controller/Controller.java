package controller;

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
import java.io.IOException;
import javax.ws.rs.core.Response;

@CrossOrigin
@RestController
@RequestMapping("/sw")
public class Controller {

  @Autowired private SentenceService sentenceService;
  @Autowired private FirebaseAuthInterface auth;

  /**
   * if you pass a string containing a phrase with an ending point, you will receive a grammatical
   * analysis of the phrase.
   *
   * @param sentence SentenceModel.
   * @return Sentence solution.
   */
  @RequestMapping(
      value = "/s",
      method = RequestMethod.POST,
      produces = "application/json",
      consumes = "application/json")
  public Response wsGetSolution(@RequestBody SentenceModel sentence) {
    try {
      return Response.ok().type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
          .entity(sentenceService.getSolution(sentence.getText()).trim())
          .build();
    } catch (IOException error) {
      error.printStackTrace();
      return Response.serverError().status(550).build(); // server di freeling non funzionante
    }
  }

  /**
   * Creates a token for the user's session and retrieves his 
   * personal information from the database.
   * @param login LoginModel.
   * @return token, uid and user information.
   */
  @RequestMapping(
      value = "/login",
      method = RequestMethod.POST,
      produces = "application/json",
      consumes = "application/json")
  public Response wsLogin(@RequestBody LoginModel login) {
    try {
      return Response.ok()
          .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
          .entity(UsersService.login(login.getEmail(), login.getPassword(), auth))
          .build();
    } catch (Exception error) {
      error.printStackTrace();
      return Response.serverError()
          .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
          .status(551)
          .build();
    }
  }

  /**
   * Create a new user and add his in db collection.
   *
   * @param newUser RegistrationModel.
   * @return A token referring to the new user and his information.
   */
  @RequestMapping(
      value = "/nu",
      method = RequestMethod.POST,
      produces = "application/json",
      consumes = "application/json")
  public Response wsNewUser(@RequestBody RegistrationModel newUser) {
    try {
      return Response.ok()
          .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
          .entity(UsersService.newUser(newUser, auth))
          .build();
    } catch (Exception error) {
      error.printStackTrace();
      return Response.serverError()
          .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
          .status(551).build();
    }
  }
}
