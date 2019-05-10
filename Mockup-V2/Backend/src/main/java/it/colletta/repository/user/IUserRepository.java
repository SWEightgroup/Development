package it.colletta.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IUserRepository<T, I> extends MongoRepository<T, I> {

  /**
   * Find the user by his email.
   *
   * @param email the email of the user
   * @return UserModel of the user with that email
   */
  Optional<T> findByEmail(final String email);

  List<T> findAllByRole(String role);

}
