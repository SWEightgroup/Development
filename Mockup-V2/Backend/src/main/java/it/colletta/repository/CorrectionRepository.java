package it.colletta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.colletta.model.Correction;

public interface CorrectionRepository extends MongoRepository<Correction, String> {
  Correction test(String Id);
}