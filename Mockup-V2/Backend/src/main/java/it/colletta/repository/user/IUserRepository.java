package it.colletta.repository.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IUserRepository<T, ID> extends MongoRepository<T, ID> {

  /**
   * Find the user by his email.
   *
   * @param email the email of the user
   * @return UserModel of the user with that email
   */
  Optional<T> findByEmail(final String email);

  List<T> findAllByRole(String role);

}
