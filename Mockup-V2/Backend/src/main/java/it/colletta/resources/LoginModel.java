package it.colletta.resources;

public class LoginModel {
  private final String email;
  private final String password;

  /**
   * LoginModel Constructor.
   * 
   * @param email User's email
   * @param password User's password
   */
  public LoginModel(String email, String password) {
    this.email = email;
    this.password = password;
  }

  /**
   * Get user's email.
   * 
   * @return User's email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Get user's password.
   * @return User's password
   */
  public String getPassword() {
    return password;
  }
}
