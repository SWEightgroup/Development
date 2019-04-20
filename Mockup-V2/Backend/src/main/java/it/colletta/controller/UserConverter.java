package it.colletta.controller;

import it.colletta.model.StudentModel;
import it.colletta.model.TeacherModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.UserHelper;
import it.colletta.security.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class UserConverter implements
    Converter<UserHelper, UserModel> {

  @Override
  public UserModel convert(UserHelper source) {
    UserModel userModel = null;
    switch (source.getRole()) {
      case Role.TEACHER:
        userModel = new TeacherModel();
        break;
      case Role.STUDENT:
        userModel = new StudentModel();
        break;
      default:
        userModel = new UserModel();
        break;
    }
    BeanUtils.copyProperties(source, userModel);
    return userModel;
  }
}
