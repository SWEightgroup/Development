package it.colletta.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
//@TypeAlias("student")
public class StudentModel extends UserModel {

  private Integer currentGoal;

  private ArrayList<String> favoriteTeacherIds;

  public StudentModel() {
    super();
    this.favoriteTeacherIds = new ArrayList<>();
    this.currentGoal = 0;
  }

  @Builder(builderMethodName = "studentBuilder")
  public StudentModel(String id, String email, String firstName, String lastName,
      String password, String role, String language, Date dateOfBirth,
      Boolean enabled) {
    super(id, email, firstName, lastName, password, role, language, dateOfBirth, enabled);
    this.favoriteTeacherIds = new ArrayList<>();
    this.currentGoal = 0;
  }



  /**
   *
   */
  public Integer getCurrentGoal() {
    return currentGoal;
  }

  /**
   *
   */
  public ArrayList<String> getFavoriteTeacherIds() {
    return favoriteTeacherIds;
  }

  public void setCurrentGoal(int currentGoal) {
    this.currentGoal = currentGoal;
  }
}
