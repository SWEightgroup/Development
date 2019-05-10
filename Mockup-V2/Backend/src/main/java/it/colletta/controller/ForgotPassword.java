package it.colletta.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import it.colletta.model.helper.ChangePasswordHelper;
import it.colletta.service.ForgotPasswordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
public class ForgotPassword {

  private ForgotPasswordService forgotPasswordService;

  @Autowired
  public ForgotPassword(ForgotPasswordService forgotPasswordService) {
    this.forgotPasswordService = forgotPasswordService;
  }


  /**
   * Generate new password forgot.
   *
   * @param passwordHelper passwordHelper.
   * @return HttpStatus of the operation.
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/forgot",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> requestNewPassword(@RequestBody ChangePasswordHelper passwordHelper) {
    try {
      forgotPasswordService.generateNewPasswordRequest(passwordHelper.getUsername(),
          linkTo(SingnupController.class).slash("/forgot-password"));
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception error) {
      error.printStackTrace();
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }

  /**
   * Change Password.
   *
   * @param passwordHelper passwordHelper.
   * @return HttpStatus of the operation.
   */
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/change",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> changePassword(@RequestBody ChangePasswordHelper passwordHelper) {
    try {
      forgotPasswordService.setNewPassword(passwordHelper);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception error) {
      error.printStackTrace();
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }

}
