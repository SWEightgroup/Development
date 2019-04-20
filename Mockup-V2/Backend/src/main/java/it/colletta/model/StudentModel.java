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

  private List<String> exercisesToDo;

  private List<String> exercisesDone;

  private ArrayList<String> favoriteTeacherIds;

  public StudentModel() {
    super();
    this.favoriteTeacherIds = new ArrayList<>();
    this.exercisesDone = new ArrayList<>();
    this.exercisesToDo = new ArrayList<>();
    this.currentGoal = 0;
  }

  @Builder(builderMethodName = "studentBuilder")
  public StudentModel(String id, String email, String firstName, String lastName,
      String password, String role, String language, Date dateOfBirth,
      Boolean enabled) {
    super(id, email, firstName, lastName, password, role, language, dateOfBirth, enabled);
    this.favoriteTeacherIds = new ArrayList<>();
    this.exercisesDone = new ArrayList<>();
    this.exercisesToDo = new ArrayList<>();
    this.currentGoal = 0;
  }

  public StudentModel(UserModel user) {
    super(user.id, user.email, user.firstName, user.lastName, user.password, user.role,
        user.language, user.dateOfBirth, user.enabled);
    this.favoriteTeacherIds = new ArrayList<>();
    this.exercisesDone = new ArrayList<>();
    this.exercisesToDo = new ArrayList<>();
    this.currentGoal = 0;
  }

  /**
   *
   */
  public Boolean addExerciseToDo(final String exerciseToAddId) {
    return exercisesToDo.add(exerciseToAddId);
  }

  /**
   *
   */
  public Boolean removeExerciseToDo(final String exerciseToRemoveId) {
    return exercisesToDo.remove(exerciseToRemoveId);
  }

  /**
   *
   */
  public Boolean addExerciseDone(final String exerciseToAddId) {
    return exercisesDone.add(exerciseToAddId);
  }

  /**
   *
   */
  public Boolean removeDone(final String exerciseToRemoveId) {
    return exercisesDone.remove(exerciseToRemoveId);
  }

  /**
   *
   */
  public List<String> getExercisesToDo() {
    return exercisesToDo;
  }

  /**
   *
   */
  public List<String> getExercisesDone() {
    return exercisesDone;
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
}
