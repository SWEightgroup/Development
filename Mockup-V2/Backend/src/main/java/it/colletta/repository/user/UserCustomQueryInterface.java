package it.colletta.repository.user;

import it.colletta.model.UserModel;

import java.util.List;

// interface for custom query on users collections
public interface UserCustomQueryInterface {

  /**
   * @param id TODO
   * @return nothing
   */
  public void updateActivateFlagOnly(String id);

  /**
   * @param id TODO
   * @return List<String> TODO

  public List<String> findAllPhrasesInserted(String id);
   */
  /**
   * @param newUser the updated version of the user
   * @param oldUser the new version of the user
   * @return the modify user
   */
  UserModel updateUser(UserModel oldUser, UserModel newUser);

}
