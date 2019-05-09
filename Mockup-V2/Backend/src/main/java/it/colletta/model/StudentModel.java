package it.colletta.model;

import lombok.Builder;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
// @TypeAlias("student")
public class StudentModel extends UserModel {

  private Integer currentGoal;

  private List<String> favoriteTeacherIds;

  public StudentModel() {
    super();
    this.favoriteTeacherIds = new ArrayList<>();
    this.currentGoal = 0;
  }

  @Builder(builderMethodName = "studentBuilder")
  public StudentModel(String id, String email, String firstName, String lastName, String password,
      String role, String language, Date dateOfBirth, Boolean enabled) {
    super(id, email, firstName, lastName, password, role, language, dateOfBirth, enabled);
    this.favoriteTeacherIds = new ArrayList<>();
    this.currentGoal = 0;
  }

  /**
   * get current goal student.
   */
  public Integer getCurrentGoal() {
    return currentGoal;
  }

  /**
   * set current goal student.
   */
  public void setCurrentGoal(int currentGoal) {
    this.currentGoal = currentGoal;
  }

  /**
   * get favorite teacher student.
   */
  public List<String> getFavoriteTeacherIds() {
    return favoriteTeacherIds;
  }

  /**
   * set favorite teacher student.
   */
  public void setFavoriteTeacherIds(List<String> favoriteTeacherIds) {
    this.favoriteTeacherIds = favoriteTeacherIds;
  }
}
