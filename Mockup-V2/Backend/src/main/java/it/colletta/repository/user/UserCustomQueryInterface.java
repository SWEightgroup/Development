package it.colletta.repository.user;

import it.colletta.model.UserModel;

import java.util.List;

// interface for custom query on users collections
public interface UserCustomQueryInterface {

  /**
   * @param id TODO
   * @return nothing
   */
  void updateActivateFlagOnly(String id);

  /**
   * @return all the user who are successfully register and activated in the system
   */
  List<UserModel> getAllUsers();
}
