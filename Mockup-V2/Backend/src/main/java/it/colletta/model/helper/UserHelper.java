package it.colletta.model.helper;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserHelper {
  @JsonProperty("username")
  private String email;
  private String firstName;
  private String lastName;
  private String password;
  private String role;
  private String language;
  private Date dateOfBirth;

}
