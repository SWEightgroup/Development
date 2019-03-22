package it.colletta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.colletta.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
  Users findByUsername(String username);
}