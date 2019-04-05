package it.colletta.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@Setter
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "users")
public class
UserModel implements UserDetails {
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
  @DBRef(lazy = true)
  private List<ExerciseModel> exercisesToDo = new ArrayList<>();

  @Builder.Default
  @DBRef(lazy = true)
  private List<ExerciseModel> exercisesDone = new ArrayList<>();

  private Boolean enabled;

  @Builder.Default
  private ArrayList<String> favoriteTeacherIds = new ArrayList<>();


  public UserModel() {
    enabled = true;
    favoriteTeacherIds = new ArrayList<>();
    exercisesToDo = new ArrayList<>();
    exercisesDone = new ArrayList<>();
  }

  public Boolean addExerciseToDo(ExerciseModel exerciseToAdd) {
    return exercisesToDo.add(exerciseToAdd);
  }
  public Boolean removeExerciseToDo(ExerciseModel exerciseToRemove) {
    return exercisesToDo.remove(exerciseToRemove);
  }

  public Boolean addExerciseDone(ExerciseModel exerciseToAdd) {
    return exercisesDone.add(exerciseToAdd);
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public List<ExerciseModel> getExercisesToDo() {
    return exercisesToDo;
  }

  public List<ExerciseModel> getExercisesDone() {
    return exercisesDone;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }

  public String getLanguage() {
    return language;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public Integer getCurrentGoal() {
    return currentGoal;
  }

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
