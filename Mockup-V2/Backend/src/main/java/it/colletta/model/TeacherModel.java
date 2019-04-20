package it.colletta.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
//@TypeAlias("teacher")
public class TeacherModel extends UserModel {

  private List<String> teacherExercise;

  public TeacherModel() {
    super();
    this.teacherExercise = new ArrayList<>();
  }

  @Builder(builderMethodName = "teacherBuilder")
  public TeacherModel(String id, String email, String firstName, String lastName,
      String password, String role, String language, Date dateOfBirth, Boolean enabled) {
    super(id, email, firstName, lastName, password, role, language, dateOfBirth, enabled);
    this.teacherExercise = new ArrayList<>();
  }

  public List<String> getTeacherExercise() {
    return teacherExercise;
  }

  public void setTeacherExercise(List<String> teacherExercise) {
    this.teacherExercise = teacherExercise;
  }
}
