package it.colletta.controller;

import it.colletta.resources.LoginModel;
import it.colletta.resources.RegistrationModel;
import it.colletta.resources.SentenceModel;
import it.colletta.service.SentenceService;
import it.colletta.service.TeacherService;
import it.colletta.service.UsersService;
import java.io.IOException;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/sw")
public class Controller {

  @Autowired private SentenceService sentenceService;
  @Autowired private UsersService userService;
  @Autowired private TeacherService teacherService;

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
      return Response.ok().type(javax.ws.rs.core.MediaType.APPLICATION_JSON).entity(text).build();
    } catch (IOException error) {
      error.printStackTrace();
      return Response.serverError().status(550).build(); // server di freeling non funzionante
    }
  }

  /**
   * Creates a token for the user's session and retrieves his personal information from the
   * database.
   *
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
      return Response.ok()	
          .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
          .entity(userService.newUser(newUser))
          .build();
    } catch (Exception error) {
      error.printStackTrace();
      return Response.serverError()
          .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
          .status(551)
          .build();
    }
  }

  @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
  @ResponseBody
  public String getTeacherSentence(@PathVariable("id") String teacherId) {
    return teacherService.getInsertedSenteces(teacherId).toString();
  }
}
