package it.colletta.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import it.colletta.model.helper.ChangePasswordHelper;
import it.colletta.service.ForgotPasswordService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/password")
public class ForgotPassword {

  private ForgotPasswordService forgotPasswordService;

  @Autowired
  public ForgotPassword(ForgotPasswordService forgotPasswordService) {
    this.forgotPasswordService = forgotPasswordService;
  }

  /**
   * Generate new password forgot.
   *
   * @param forgotObject forgotObject.
   * @return HttpStatus of the operation.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/forgot", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> requestNewPassword(Map<String, String> forgotObject) {
    try {
      forgotPasswordService.generateNewPasswordRequest(
          forgotObject, linkTo(SingnupController.class).slash("/aaa/aaaa")
      );
      return new ResponseEntity<>(HttpStatus.OK);
    }
    catch (Exception e) {
      return new ResponseEntity<>(e.getStackTrace(), HttpStatus.SERVICE_UNAVAILABLE);
    }
  }
  /**
   * Change Password.
   *
   * @param passwordHelper passwordHelper.
   * @return HttpStatus of the operation.
   */
  @RequestMapping(method = RequestMethod.POST, value = "/change", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> changePassword(ChangePasswordHelper passwordHelper) {
    try {
      forgotPasswordService.setNewPassword(passwordHelper);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    catch (Exception e) {
      return new ResponseEntity<>(e.getStackTrace(), HttpStatus.SERVICE_UNAVAILABLE);
    }
  }


}
