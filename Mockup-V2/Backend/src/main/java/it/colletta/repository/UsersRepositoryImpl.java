package it.colletta.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import it.colletta.model.UserModel;

@Repository
public class UsersRepositoryImpl implements UserCustomQueryInterface {

  MongoTemplate mongoTemplate;
  

  
  @Autowired
  public UsersRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }
  
  /**
  * @param id TODO 
  * @return nothing
  */  
  @Override
  public void updateActivateFlagOnly(String id) {
    Query query = new Query(Criteria.where("_id").is(id));
    mongoTemplate.updateFirst(query, Update.update("actived", true), UserModel.class);
  }

  /**
  * @param id TODO 
  * @return List<String> TODO 
  */ 
  @Override
  public List<String> findAllPhrasesInserted(String id) {
    Query query = new Query(Criteria.where("_id").is(id)) ;
    UserModel userModel = mongoTemplate.findOne(query, UserModel.class);
    return userModel.getPhrases();
  }
}
