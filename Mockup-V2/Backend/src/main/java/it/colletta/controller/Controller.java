package it.colletta.controller;

import it.colletta.resources.LoginModel;
import it.colletta.resources.RegistrationModel;
import it.colletta.resources.SentenceModel;
import it.colletta.service.SentenceService;
import it.colletta.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

@CrossOrigin
@RestController
@RequestMapping("/sw")
public class Controller {

  @Autowired private SentenceService sentenceService;
  @Autowired private UsersService userService;

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
      String text = sentenceService.getSolution(sentence.getText()).trim();
      return Response.ok().type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
          .entity(text)
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
          .entity(userService.login(login.getEmail(), login.getPassword()))
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
      
      Map<String, Object> x = userService.newUser(newUser);
      
      return Response.ok()
          .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
          .entity(x)
          .build();
    } catch (Exception error) {
      error.printStackTrace();
      return Response.serverError()
          .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
          .status(551).build();
    }
  }
}
