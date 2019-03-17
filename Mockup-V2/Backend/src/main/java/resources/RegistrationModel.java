package resources;

import org.springframework.boot.json.GsonJsonParser;
import java.sql.Timestamp;
import java.util.Map;
import com.google.gson.Gson;

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
   * @param firstName String
   * @param lastName String
   * @param birthDate Timestamp
   * @param role String
   * @param email String
   * @param password String
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
   * @return fisrtName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * return user lastName.
   * @return lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * return user birthDate.
   * @return birthDate
   */
  public Timestamp getBirthDate() {
    return birthDate;
  }

  /**
   * return user role.
   * @return role
   */
  public String getRole() {
    return role;
  }

  /**
   * return user email.
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * return user password.
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * returns all user information, except password.
   * @return user informatio
   */
  public Map<String, Object> gerUserModel() {
    Gson gson = new Gson();
    GsonJsonParser user = new GsonJsonParser();
    Map<String, Object> userMap = user.parseMap(gson.toJson(this));
    userMap.remove("password");
    return userMap;
  }

}
