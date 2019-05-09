package it.colletta.security;

public class SecurityConstants {

  public static final String SECRET = "SecretKeyToGenJWTs";
  public static final long EXPIRATION_TIME = 864_000_000; // 10 days
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGN_UP_URL = "/users/sign-up";
  public static final String ACTIVATION_URL = "/sign-up/activate";
  public static final String CHANGE_PASS_URL = "/password/change";
  public static final String FORGOT_REQUEST_URL = "/password/forgot";

}
