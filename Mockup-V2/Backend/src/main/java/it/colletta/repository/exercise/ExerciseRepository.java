package it.colletta.repository.exercise;

import it.colletta.model.ExerciseModel;
import it.colletta.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository<ExerciseModel, String> {
    
    @Override
    Iterable<ExerciseModel> findAllById(Iterable<String> ids);
import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends MongoRepository<ExerciseModel, String>, ExerciseCustomQueryInterface {

    @Override
    public Optional<ExerciseModel> findById(String id);



}
