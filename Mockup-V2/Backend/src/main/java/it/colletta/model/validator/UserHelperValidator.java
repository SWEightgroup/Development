package it.colletta.model.validator;

import it.colletta.model.helper.UserHelper;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserHelperValidator implements Validator {

  /**
   * This Validator validates *just* ClassHelper instances.
   */
  public boolean supports(Class clazz) {
    return UserHelper.class.equals(clazz);
  }


  /**
   * Validator.
   */
  public void validate(Object obj, Errors err) {
    ValidationUtils.rejectIfEmpty(err, "email", "email.empty");
    ValidationUtils.rejectIfEmpty(err, "firstName", "firstName.empty");
    ValidationUtils.rejectIfEmpty(err, "lastName", "lastName.empty");
    ValidationUtils.rejectIfEmpty(err, "password", "password.empty");
    ValidationUtils.rejectIfEmpty(err, "role", "role.empty");
    ValidationUtils.rejectIfEmpty(err, "language", "language.empty");
    ValidationUtils.rejectIfEmpty(err, "dateOfBirth", "dateOfBirth.empty");
    UserHelper par = (UserHelper) obj;
  }
}
