package it.colletta.repository.user;

import it.colletta.model.UserModel;
import it.colletta.security.Role;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository
    extends IUserRepository<UserModel, String>, UserCustomQueryInterface {

  /**
   * Delete user from the system.
   *
   * @param id the user id
   */
  @Override
  void deleteById(final String id);

  /**
   * @return List of Disabled users developer.
   */
  @Query("{$and : [{role : '" + Role.DEVELOPER + "'}, {enabled : false}]}")
  List<UserModel> findAllDeveloperDisabled();
}
