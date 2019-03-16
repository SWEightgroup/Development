package resources;

import java.util.Date;

public class UserModel {
	private final String firstName;
	private final String lastName;
	private final Date birthDay;
	private final String role;
	private final String email;

	public UserModel(String firstName,String lastName,Date birthDate,String role,String email) {
		this.firstName = firstName;
		this.lastName=lastName;
		this.birthDay = birthDate;
		this.role = role;
		this.email = email;
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

}
