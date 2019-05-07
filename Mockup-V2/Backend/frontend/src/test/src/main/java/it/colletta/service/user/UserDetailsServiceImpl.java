package it.colletta.service.user;

import it.colletta.model.UserModel;
import it.colletta.repository.user.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsersRepository applicationUserRepository;

    /**
     * Constructor.
     *
     * @param applicationUserRepository User Repository
     */
    public UserDetailsServiceImpl(UsersRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    private static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    private List<String> getRoles(String role) {
        ArrayList<String> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        UserModel applicationUser = applicationUserRepository.findByEmail(email);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }

        return org.springframework.security.core.userdetails.User.withUsername(email)
                .password(applicationUser.getPassword()).authorities(applicationUser.getAuthorities())
                .disabled(!applicationUser.isEnabled()).build();
    }
}
