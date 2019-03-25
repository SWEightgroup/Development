package it.colletta.security;

import javax.xml.bind.DatatypeConverter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.xml.bind.DatatypeConverter;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.SECRET;

public class ParseJWT {
    //Sample method to validate and read the JWT
    public static String parseJWT(String jwt) {
        jwt = jwt.replace("Bearer", "");
        try {
            DecodedJWT decodedJWT = JWT.decode(jwt);
            String subject = decodedJWT.getSubject();
            return subject;
        }
        catch(JWTDecodeException e) {
            return e.getMessage();
        }
    }
}
