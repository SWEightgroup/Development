package resources;

import java.util.Date;

public class RegistrationModel {
	private final String firstName;
	private final String lastName;
	private final Date birthDay;
	private final String role;
	private final String email;
	private final String password;
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param role
	 * @param email
	 * @param password
	 */
	public RegistrationModel(String firstName,String lastName,Date birthDate,String role,String email,String password) {
		this.firstName = firstName;
		this.lastName=lastName;
		this.birthDay = birthDate;
		this.role = role;
		this.email = email;
		this.password=password;
		
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @return
	 */
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * 
	 * @return
	 */
	public String getRole() {
		return role;
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

	/**
	 * 
	 * @return
	 */
	public LoginModel getLoginModel() {
		return new LoginModel(email,password);
	}

	/**
	 * 
	 * @return
	 */
	public UserModel getUserModel() {
		return new UserModel(this.firstName, this.lastName, this.birthDay, this.role, this.email);
	}
}
