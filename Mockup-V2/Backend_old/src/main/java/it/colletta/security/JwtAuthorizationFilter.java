package it.colletta.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static it.colletta.security.SecurityConstants.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService userDetailsService;

    /**
     * Constructor for class JWTAuthorizationFilter.
     *
     * @param AuthenticationManager TODO explain its purpose
     */
    public JwtAuthorizationFilter(AuthenticationManager authManager, UserDetailsService userDetailsService) {
        super(authManager);
        this.userDetailsService = userDetailsService;
    }

    /**
     * @param HttpServletRequest  TODO
     * @param HttpServletResponse TODO
     * @param FilterChain         TODO
     * @return nothing
     * @throws IOException      TODO
     * @throws ServletException TODO
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    /**
     * @param request TODO
     * @return UsernamePasswordAuthenticationToken TODO
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
                    .verify(token.replace(TOKEN_PREFIX, "")).getSubject();


            //ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            //authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
