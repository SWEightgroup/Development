package it.colletta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Collection;
import java.util.Date;
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

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "users")
public class UserModel implements UserDetails {

  private static final long serialVersionUID = 1L;
  @Id
  protected String id;

  @JsonProperty("username")
  @Indexed(unique = true)
  protected String email;

  protected String firstName;
  protected String lastName;

  @JsonProperty(access = Access.WRITE_ONLY)
  protected String password;

  protected String role;
  protected String language;
  protected Date dateOfBirth;

  protected Boolean enabled;

  /**
   * get id.
   */
  public String getId() {
    return id;
  }

  /**
   * get first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * get last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * get password.
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * get role.
   */
  public String getRole() {
    return role;
  }

  /**
   * get language.
   */
  public String getLanguage() {
    return language;
  }

  /**
   * get date of birth.
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * get Authorities.
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.createAuthorityList(role);
  }

  /**
   * get username.
   */
  @Override
  public String getUsername() {
    return email;
  }

  /**
   * return status of account.
   *
   * @return true or false.
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * return status of account.
   *
   * @return true or false.
   */
  @Override
  public boolean isAccountNonLocked() {
    return enabled;
  }

  /**
   * return status of credentials.
   *
   * @return true or false.
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * return status of user.
   *
   * @return true or false.
   */
  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
