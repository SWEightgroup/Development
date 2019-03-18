package resources;

public class LoginModel {
  private final String email;
  private final String password;

  /**
   * LoginModel Constructor.
   * 
   * @param email
   * @param password
   */
  public LoginModel(String email, String password) {
    this.email = email;
    this.password = password;
  }

  /**
   * user email.
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * user password.
   * @return password
   */
  public String getPassword() {
    return password;
  }
}
