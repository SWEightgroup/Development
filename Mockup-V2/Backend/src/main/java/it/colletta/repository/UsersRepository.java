package it.colletta.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import it.colletta.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends MongoRepository<UserModel, String>, UserCustomQueryInterface {

  UserModel findByEmail(String email);

  Optional<UserModel> findById(String id);

  @Override
  void delete(UserModel users);

  @Override
  void deleteById(String s);

  @Override
  List<UserModel> findAll();

}