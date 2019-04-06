package it.colletta.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class ParseJWT {

  /**
   * Sample method to validate and read the JWT
   *
   * @param jwt the actual token of a user
   * @return String
   */
  public static String getEmailFromJWT(String jwt) {
    jwt = jwt.replace("Bearer", "");
    try {
      DecodedJWT decodedJWT = JWT.decode(jwt);
      String subject = decodedJWT.getSubject();
      return subject;
    } catch (JWTDecodeException e) {
      return e.getMessage();
    }
  }

  public static String getIdFromJwt(String jwt) {
    jwt = jwt.replace("Bearer", "");
    try {
      DecodedJWT decodedJWT = JWT.decode(jwt);
      String id = decodedJWT.getId();
      return id;
    } catch (JWTDecodeException e) {
      return e.getMessage();
    }
  }
}
