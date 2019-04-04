package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.ExerciseModel;
import it.colletta.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;


public interface ExerciseRepository extends MongoRepository<ExerciseModel, String> {

  @Override
  Iterable<ExerciseModel> findAllById(Iterable<String> ids);

  @Override
  Optional<ExerciseModel> findById(String id);


  UpdateResult modifyAuthorName(String newAuthorName, String teacherId);

}
