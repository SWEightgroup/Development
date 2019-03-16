package resources;

public class LoginModel {
	private final String email;
	private final String password;

	/**
	 * 
	 * @param email
	 * @param password
	 */
	public LoginModel(String email,String password) {
		this.email = email;
		this.password=password;

	}

	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
}
