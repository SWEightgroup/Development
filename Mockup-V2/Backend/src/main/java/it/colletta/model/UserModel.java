package it.colletta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "users")
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

  @Builder.Default
  private List<String> exercisesToDo = new ArrayList<>();

  @Builder.Default
  private List<String> exercisesDone = new ArrayList<>();

  private Boolean enabled;

  @Builder.Default
  private ArrayList<String> favoriteTeacherIds = new ArrayList<>();

  /**
   * 
   * @param exerciseToAddId
   * @return
   */
  public Boolean addExerciseToDo(String exerciseToAddId) {
    if (exercisesToDo == null) {
      exercisesToDo = new ArrayList<>();
    }
    return exercisesToDo.add(exerciseToAddId);
  }

  /**
   * 
   * @param exerciseToRemoveId
   * @return
   */
  public Boolean removeExerciseToDo(String exerciseToRemoveId) {
    return exercisesToDo.remove(exerciseToRemoveId);
  }

  /**
   * 
   * @param exerciseToAddId
   * @return
   */
  public Boolean addExerciseDone(String exerciseToAddId) {
    if (exercisesDone == null) {
      exercisesDone = new ArrayList<>();
    }
    return exercisesDone.add(exerciseToAddId);
  }

  /**
   * 
   * @return
   */
  public String getId() {
    return id;
  }

  /**
   * 
   * @return
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * 
   * @return
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * 
   * @return
   */
  public List<String> getExercisesToDo() {
    return exercisesToDo;
  }

  /**
   * 
   * @return
   */
  public List<String> getExercisesDone() {
    return exercisesDone;
  }

  @Override
  public String getPassword() {
    return password;
  }

  /**
   * 
   * @return
   */
  public String getRole() {
    return role;
  }

  /**
   * 
   * @return
   */
  public String getLanguage() {
    return language;
  }

  /**
   * 
   * @return
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * 
   * @return
   */
  public Integer getCurrentGoal() {
    return currentGoal;
  }

  /**
   * 
   * @return
   */
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
