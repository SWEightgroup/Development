package it.colletta.resources;

import com.google.gson.Gson;

import org.springframework.boot.json.GsonJsonParser;

import java.sql.Timestamp;
import java.util.Map;

public class RegistrationModel {
  private final String firstName;
  private final String lastName;
  private final Timestamp birthDate;
  private final String role;
  private final String email;
  private final String password;

  /**
   * RegistrationModel constructor.
   * 
   * @param firstName User's firstName.
   * @param lastName User's lastName.
   * @param birthDate User's birthDate.
   * @param role User's role.
   * @param email User's email.
   * @param password User's password
   */
  public RegistrationModel(
      String firstName,
      String lastName,
      Timestamp birthDate,
      String role,
      String email,
      String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.role = role;
    this.email = email;
    this.password = password;
  }

  /**
   * return user fisrtName.
   * @return User's fisrtName.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * return user lastName.
   * @return User's lastName.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * return user birthDate.
   * @return User's birthDate.
   */
  public Timestamp getBirthDate() {
    return birthDate;
  }

  /**
   * return user role.
   * @return User's role.
   */
  public String getRole() {
    return role;
  }

  /**
   * return user email.
   * @return User's email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * return user password.
   * @return User's password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * returns all user information, except password.
   * @return user's information.
   */
  public Map<String, Object> gerUserModel() {
    Gson gson = new Gson();
    GsonJsonParser user = new GsonJsonParser();
    Map<String, Object> userMap = user.parseMap(gson.toJson(this));
    userMap.remove("password");
    return userMap;
  }

}
