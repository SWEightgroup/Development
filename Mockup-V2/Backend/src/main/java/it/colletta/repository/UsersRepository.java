package it.colletta.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import it.colletta.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends MongoRepository<UserModel, String>, UserCustomQueryInterface {

  /**
  * @param email TODO 
  * @return UserModel TODO 
  */
  UserModel findByEmail(String email);

  /**
  * @param id TODO 
  * @return Optional<UserModel> TODO 
  */
  Optional<UserModel> findById(String id);

  /**
  * @param users TODO 
  * @return nothing 
  */
  @Override
  void delete(UserModel users);

  /**
  * @param s TODO 
  * @return nothing 
  */
  @Override
  void deleteById(String s);

  /**
  * @param nothing 
  * @return List<UserModel> return all the user 
  */
  @Override
  List<UserModel> findAll();

}