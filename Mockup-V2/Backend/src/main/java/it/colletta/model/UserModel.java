package it.colletta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "users")
public class UserModel implements UserDetails {
  @Id
  private String id;
  @JsonProperty("username")
  @Indexed(unique = true)
  private String email;
  private String firstName; 
  private String lastName; 
  private String password;
  private String role;
  private String language;
  private Date dateOfBirth;
  private TimeZone userTimeZone;
  private Integer currentGoal;
  private ArrayList<String> insertedExercises;     //exercises inserted by teachers
  private ArrayList<String> execiseToDo;    //array list of exerciseToDo 
  private Boolean activated = true;
  private ArrayList<String> favoriteTeacherIds;

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
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

  public ArrayList<String> getInsertedExercise() {
    return insertedExercises;
  }

  public ArrayList<String> getExeciseToDo() {
    return execiseToDo;
  }

  public Boolean getActivated() {
    return activated;
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
    return true; //TODO DECOMENNTARE
    //return activated;
  }
}
