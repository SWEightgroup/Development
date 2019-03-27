package it.colletta.service;

import it.colletta.model.UserModel;
import it.colletta.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UsersRepository applicationUserRepository;

    public UserDetailsServiceImpl(UsersRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserModel applicationUser = applicationUserRepository.findByEmail(email);
    if (applicationUser == null) {
      throw new UsernameNotFoundException(email);
    }
    return new org.springframework.security.core.userdetails.User(
        applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

}
