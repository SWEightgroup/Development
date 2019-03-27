package it.colletta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
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
  private Integer currentGoal;
  private ArrayList<String> exercises;     //array list of reference
  private ArrayList<String> execiseToDo;    //array list of exercise
  private Boolean activated;
  private ArrayList<String> favoriteTeacherIds;

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
    return activated;
  }

  @Override
  public boolean isAccountNonLocked() {
    return activated;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return activated;
  }

  @Override
  public boolean isEnabled() {
    return activated;
  }
}
