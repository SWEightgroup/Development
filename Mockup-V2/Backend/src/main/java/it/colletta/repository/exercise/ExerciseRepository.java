package it.colletta.repository.exercise;

import it.colletta.model.ExerciseModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository<ExerciseModel, String> {


}
