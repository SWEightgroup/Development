package it.colletta.repository;

import it.colletta.model.UserModel;

public interface UserCustomQueryInterface {
  public UserModel updateActivateFlagOnly(String id);

}
