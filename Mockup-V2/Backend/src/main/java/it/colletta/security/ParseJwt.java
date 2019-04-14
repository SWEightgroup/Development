package it.colletta.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class ParseJwt {

  /**
   * Return email from token.
   *
   * @param jwt the actual token of a user
   * @return String Email
   */
  public static String getEmailFromJwt(String jwt) {
    jwt = jwt.replace("Bearer", "");
    try {
      DecodedJWT decodedJwt = JWT.decode(jwt);
      String subject = decodedJwt.getSubject();
      return subject;
    } catch (JWTDecodeException error) {
      error.printStackTrace();
      throw error;
    }
  }

  /**
   * Return id from token.
   * 
   * @param jwt Token
   * @return Id 
   */
  public static String getIdFromJwt(String jwt) {
    jwt = jwt.replace("Bearer", "");
    try {
      DecodedJWT decodedJwt = JWT.decode(jwt);
      String id = decodedJwt.getId();
      return id;
    } catch (JWTDecodeException error) {
      error.printStackTrace();
      throw error;
    }
  }
}
