package it.colletta.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import it.colletta.model.UserModel;
import it.colletta.service.SingupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingnupController {
  private SingupService singupService;

  @Autowired
  public void setSingupService(SingupService singupService) {this.singupService = singupService;}

  /**
   * @param user the user obj with username and password
   * @return ResponseEntity if the operation completed correctly otherwise return an error response.
   */
  @RequestMapping(value = "/sign-up", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> signUp(@RequestBody UserModel user) {
    try {
      if (singupService.addUser(user, linkTo(SingnupController.class).slash("activate")) != null) {
        return new ResponseEntity<>(user, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
      }
    }
    catch(org.springframework.dao.DuplicateKeyException e) {
      return new ResponseEntity<>("User already exsist", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  @RequestMapping(value = "/sign-up/activate/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> activateUser(@RequestParam("id") String requestId) {
    try {
      singupService.setEnabledToTrue(requestId);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    catch(ResourceNotFoundException exception) {
      return new ResponseEntity<String>("Signup request not found", HttpStatus.BAD_REQUEST);
    }
  }
}
