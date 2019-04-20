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
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "users")
@JsonInclude(Include.NON_NULL)
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
