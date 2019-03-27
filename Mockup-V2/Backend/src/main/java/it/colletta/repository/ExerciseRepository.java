package it.colletta.repository;

import it.colletta.model.ExerciseModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository<ExerciseModel, String> {


}
