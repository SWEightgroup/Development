package it.colletta.repository;

import lombok.Builder;
import org.springframework.data.mongodb.repository.MongoRepository;

import it.colletta.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {
  Users findByUsername(String username);

  @Override
  void delete(Users users);

  @Override
  void deleteById(String s);

  @Override
  List<Users> findAll();
}