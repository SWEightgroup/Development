package resources;

import java.util.Date;
import java.util.Map;

import org.springframework.boot.json.GsonJsonParser;

import com.google.gson.Gson;

public class RegistrationModel {
	private final String firstName;
	private final String lastName;
	private final Date birthDate;
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
		this.birthDate = birthDate;
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
	public Date getBirthDate() {
		return birthDate;
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
	
	public Map<String,Object> gerUserModel() {
		Gson gson = new Gson();
		GsonJsonParser user= new GsonJsonParser();
		Map<String, Object> userMap =user.parseMap(gson.toJson(this));
		userMap.remove("password");
		return userMap;
		
	}

	/**
	 * 
	 * @return
	 */
	/*public LoginModel getLoginModel() {
		return new LoginModel(this.email,this.password);
	}*/

	/**
	 * 
	 * @return
	 */
	/*public UserModel getUserModel() {
		return new UserModel(this.firstName, this.lastName, this.birthDate, this.role, this.email);
	}*/
}
