package it.colletta.repository.user;

import it.colletta.model.UserModel;
import it.colletta.security.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersRepositoryImpl implements UserCustomQueryInterface {

  private MongoTemplate mongoTemplate;

  /**
   * Constructor.
   *
   * @param mongoTemplate mongoTemplate
   */
  @Autowired
  public UsersRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void updateActivateFlagOnly(final String id) {
    Query query = new Query(Criteria.where("_id").is(id));
    mongoTemplate.updateFirst(query, Update.update("enabled", true), UserModel.class);
  }

  @Override
  public List<UserModel> getAllUsers() {
    Query query = new Query();
    query.fields().exclude("exercisesToDo").exclude("exercisesDone").exclude("favoriteTeacherIds");
    query.addCriteria(Criteria.where("role").ne(Role.ADMIN));
    return mongoTemplate.find(query, UserModel.class);
  }

  @Override
  public List<UserModel> findAllByList(final List<String> userId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").in(userId));
    query.fields().exclude("favoriteTeacherIds");
    return mongoTemplate.find(query, UserModel.class);
  }

  @Override
  public Page<UserModel> findAllByRolePage(Pageable page, String role) {
    Query query = new Query(Criteria.where("role").is(role)).with(page);
    return new PageImpl<>(mongoTemplate.find(query, UserModel.class), page,
        mongoTemplate.count(query, UserModel.class));
  }
}
