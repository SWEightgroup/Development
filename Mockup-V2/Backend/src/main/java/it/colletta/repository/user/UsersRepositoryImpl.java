package it.colletta.repository.user;

import java.util.List;

import it.colletta.repository.user.UserCustomQueryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import it.colletta.model.ExerciseModel;
import it.colletta.model.UserModel;

@Repository
public class UsersRepositoryImpl implements UserCustomQueryInterface {

  private MongoTemplate mongoTemplate;



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
    mongoTemplate.updateFirst(query, Update.update("enabled", true), UserModel.class);
  }

  /**
   * return all phrases inserted by a user without corrections
   * hint user --> correction -->
   * @param id TODO
   * @return List<String> TODO
   @Override
   public List<ExerciseModel> findAllPhrasesInserted(String id) {
   Query query = new Query(Criteria.where("_id").is(id)) ;
   UserModel userModel = mongoTemplate.findOne(query, UserModel.class);
   return userModel.();
   }*/
}
