package it.colletta.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
   *
   */
  public String getId() {
    return id;
  }

  /**
   *
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   *
   */
  public String getLastName() {
    return lastName;
  }

  @Override
  public String getPassword() {
    return password;
  }

  /**
   *
   */
  public String getRole() {
    return role;
  }

  /**
   *
   */
  public String getLanguage() {
    return language;
  }

  /**
   *
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
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
