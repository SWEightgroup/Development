package service;

import resources.LoginModel;
import resources.UserModel;

public interface FirebaseAuthInterface {

	/**
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public String getUid(String token) throws Exception;
	/**
	 * 
	 * @param loginData
	 * @return
	 * @throws Exception
	 */
	public String createUser(LoginModel loginData) throws Exception;
	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public String getToken(String email, String password) throws Exception; 
	/**
	 * 
	 * @param token
	 * @param uid
	 */
	public void deleteUser(String token, String uid);
	/**
	 * 
	 * @param token
	 * @param user
	 * @return
	 */
	public UserModel updateUser(String token, UserModel user);
	/**
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public String createToken(String uid) throws Exception;
}
