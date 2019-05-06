package it.colletta.model.validator;

import it.colletta.model.helper.UserHelper;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserHelperValidator implements Validator {

  /**
   * This Validator validates *just* ClassHelper instances
   */
  public boolean supports(Class clazz) {
    return UserHelper.class.equals(clazz);
  }

  public void validate(Object obj, Errors e) {
    ValidationUtils.rejectIfEmpty(e, "email", "email.empty");
    ValidationUtils.rejectIfEmpty(e, "firstName", "firstName.empty");
    ValidationUtils.rejectIfEmpty(e, "lastName", "lastName.empty");
    ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
    ValidationUtils.rejectIfEmpty(e, "role", "role.empty");
    ValidationUtils.rejectIfEmpty(e, "language", "language.empty");
    ValidationUtils.rejectIfEmpty(e, "dateOfBirth", "dateOfBirth.empty");
    UserHelper p = (UserHelper) obj;
  }
}
