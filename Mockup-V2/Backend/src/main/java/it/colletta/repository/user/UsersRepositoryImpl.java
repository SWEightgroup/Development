package it.colletta.repository.user;

import it.colletta.model.UserModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

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

  @Override
  public List<UserModel> getAllUsers() {
    Query query = new Query();
    query.fields().exclude("exercisesToDo").exclude("exercisesDone").exclude("favoriteTeacherIds");
    query.addCriteria(Criteria.where("role").ne("ROLE_ADMIN"));
    return mongoTemplate.find(query, UserModel.class);
  }
}
