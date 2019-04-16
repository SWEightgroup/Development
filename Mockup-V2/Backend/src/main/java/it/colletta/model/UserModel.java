package it.colletta.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Setter
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "users")
@JsonInclude(Include.NON_NULL)
public class UserModel implements UserDetails {

  private static final long serialVersionUID = 1L;
  @Id
  private String id;

  @JsonProperty("username")
  @Indexed(unique = true)
  private String email;

  private String firstName;
  private String lastName;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  private String role;
  private String language;
  private Date dateOfBirth;
  private Integer currentGoal;

  private List<String> exercisesToDo;

  private List<String> exercisesDone;

  private Boolean enabled;

  private ArrayList<String> favoriteTeacherIds;

  private List<String> teacherExercise;

  public UserModel() {
    this.favoriteTeacherIds = null;
    this.exercisesDone = null;
    this.exercisesToDo = null;
    this.currentGoal = null;
    this.teacherExercise = null;
  }
  /**
   * @param exerciseToAddId
   * @return
   */
  public Boolean addExerciseToDo(final String exerciseToAddId) {
    return exercisesToDo.add(exerciseToAddId);
  }

  /**
   * @param exerciseToRemoveId
   * @return
   */
  public Boolean removeExerciseToDo(final String exerciseToRemoveId) {
    return exercisesToDo.remove(exerciseToRemoveId);
  }

  /**
   * @param exerciseToAddId
   * @return
   */
  public Boolean addExerciseDone(final String exerciseToAddId) {
    return exercisesDone.add(exerciseToAddId);
  }

  /**
   * @param exerciseToRemoveId
   * @return
   */
  public Boolean removeDone(final String exerciseToRemoveId) {
      return exercisesDone.remove(exerciseToRemoveId);
  }
  /** @return */
  public String getId() {
    return id;
  }

  /** @return */
  public String getFirstName() {
    return firstName;
  }

  /** @return */
  public String getLastName() {
    return lastName;
  }

  /** @return */
  public List<String> getExercisesToDo() {
    return exercisesToDo;
  }

  /** @return */
  public List<String> getExercisesDone() {
    return exercisesDone;
  }

  @Override
  public String getPassword() {
    return password;
  }

  /** @return */
  public String getRole() {
    return role;
  }

  /** @return */
  public String getLanguage() {
    return language;
  }

  /** @return */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  /** @return */
  public Integer getCurrentGoal() {
    return currentGoal;
  }

  /** @return */
  public ArrayList<String> getFavoriteTeacherIds() {
    return favoriteTeacherIds;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.createAuthorityList(role);
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true; // TODO DECOMENNTARE
    // return enabled;
  }
}
