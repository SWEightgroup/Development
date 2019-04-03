package it.colletta.repository.user;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
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

  // WE CAN DO BETTER, email e password non si possono cambiare da qui
  public UserModel updateUser(UserModel user, UserModel newUser){

    //TODO compete al service
      Optional<String> newFirstName = Optional.ofNullable(newUser.getFirstName());
      Optional<String> newLastName = Optional.ofNullable(newUser.getLastName());
      Optional<String> newLanguageName = Optional.ofNullable(newUser.getLanguage());
      Optional<Date> newDateOfBirth = Optional.ofNullable(newUser.getDateOfBirth());

      if(newFirstName.isPresent()){
          user.setFirstName(newFirstName.get());
      }
      if(newLastName.isPresent()){
          user.setLastName(newLastName.get());
      }
      if(newLanguageName.isPresent()){
          user.setLanguage(newLanguageName.get());
      }
      if(newDateOfBirth.isPresent()){
          user.setDateOfBirth(newDateOfBirth.get());
      }
      return mongoTemplate.save(user);

  }

}
