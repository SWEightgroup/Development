package it.colletta.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.UserModel;
import it.colletta.repository.user.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.HEADER_STRING;
import static it.colletta.security.SecurityConstants.SECRET;
import static it.colletta.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private UsersRepository usersRepository;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UsersRepository usersRepository) {
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
    }

    /**
    * @param req TODO
    * @param res TODO
    * @exception AuthenticationException
    * @return Authentication TODO 
    */ 
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            UserModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserModel.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * @param req TODO
    * @param res    TODO
    * @param auth TODO
    * @exception IOException TODO 
    * @exception ServletException TODO 
    * @return nothing 
    */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String email = ((User) auth.getPrincipal()).getUsername();
        UserModel userModel = usersRepository.findByEmail(email);
        userModel.setPassword(null);
        String token = JWT.create().withJWTId(userModel.getId())
                .withSubject(email)
                //.withClaim("id", usersRepository.findByEmail(email).getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.setHeader("Access-Control-Expose-Headers", "Authorization");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write((new ObjectMapper()).writeValueAsString(userModel));
        res.getWriter().flush();
        res.getWriter().close();
    }

}