package it.colletta.resources;

public class LoginModel {
  private final String username;
  private final String password;

  /**
   * LoginModel Constructor.
   * 
   * @param username User's email
   * @param password User's password
   */
  public LoginModel(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * Get user's email.
   * 
   * @return User's email.
   */
  public String getEmail() {
    return username;
  }

  /**
   * Get user's password.
   * 
   * @return User's password
   */
  public String getPassword() {
    return password;
  }
}
