package it.colletta.model.validator;

import it.colletta.model.helper.ClassHelper;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ClassHelperValidator implements Validator {

  /**
   * This Validator validates *just* ClassHelper instances
   */
  public boolean supports(Class clazz) {
    return ClassHelper.class.equals(clazz);
  }

  public void validate(Object obj, Errors e) {
    ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
    ClassHelper p = (ClassHelper) obj;
  }
}
