package it.colletta.repository;

import lombok.Builder;
import org.springframework.data.mongodb.repository.MongoRepository;

import it.colletta.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends MongoRepository<UserModel, String> {
  UserModel findByEmail(String email);

  @Override
  void delete(UserModel users);

  @Override
  void deleteById(String s);

  @Override
  List<UserModel> findAll();
}