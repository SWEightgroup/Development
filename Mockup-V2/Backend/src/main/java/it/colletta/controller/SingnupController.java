package it.colletta.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import it.colletta.model.UserModel;
import it.colletta.model.helper.UserHelper;
import it.colletta.model.validator.UserHelperValidator;
import it.colletta.service.SingupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SingnupController {

  private SingupService singupService;

  @InitBinder
  protected void initBinderClass(WebDataBinder binder) {
    binder.setValidator(new UserHelperValidator());
  }


  @Autowired
  public void setSingupService(SingupService singupService) {
    this.singupService = singupService;
  }

  /**
   * @param userDataTransferObject the user obj with username and password
   * @return ResponseEntity if the operation completed correctly otherwise return an error response.
   */
  @RequestMapping(
      value = "/sign-up",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> signUp(@Valid @RequestBody UserHelper userDataTransferObject) {

    userDataTransferObject.setEmail(userDataTransferObject.getEmail().toLowerCase());
    UserModel user = (new UserConverter().convert(userDataTransferObject));
    try {
      if (singupService.addUser(user, linkTo(SingnupController.class).slash("/sign-up/active"))
          != null) {
        return new ResponseEntity<>(user, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
      }
    } catch (Exception error) {
      error.printStackTrace();
      return new ResponseEntity<>(error.getClass().getCanonicalName(),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  /**
   * setEnabledToTrue.
   * 
   * @param requestId Request Id
   */
  @RequestMapping(
      value = "/sign-up/activate/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> activateUser(@PathVariable("id") String requestId) {
    try {
      singupService.setEnabledToTrue(requestId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (ResourceNotFoundException exception) {
      return new ResponseEntity<>(exception.getClass().getCanonicalName(), HttpStatus.BAD_REQUEST);
    }
  }

}
