package it.colletta.service.user;

import it.colletta.model.UserModel;
import it.colletta.repository.user.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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


  @Override
  public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
    return applicationUserRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }


}
