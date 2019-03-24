package it.colletta.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserCustomQuery implements  UserCustomQueryInterface {

  @Autowired
  MongoTemplate mongoTemplate;
  @Override
  public void activateUser(String id) {
    //TODO
  }
}
