package service;

import java.util.Map;

public interface FirebaseAuthInterface {

  /**
   * @param token
   * @return
   * @throws Exception
   */
  public String getUid(String token) throws Exception;
  /**
   * @param loginData
   * @return
   * @throws Exception
   */
  public String createUser(String email, String password) throws Exception;
  /**
   * @param email
   * @param password
   * @return
   * @throws Exception
   */
  public String getToken(String email, String password) throws Exception;
  /**
   * @param token
   * @param uid
   */
  public void deleteUser(String token, String uid);
  /**
   * @param token
   * @param user
   * @return
   */
  public Map<String, Object> updateUser(String token, Map<String, Object> user);
  /**
   * @param uid
   * @return
   * @throws Exception
   */
  public String createToken(String uid) throws Exception;
}
