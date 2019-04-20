package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.ExerciseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends MongoRepository<ExerciseModel, String>,
    ExerciseCustomQueryInterface {

  @Override
  Iterable<ExerciseModel> findAllById(Iterable<String> ids);

  UpdateResult modifyAuthorName(final String newAuthorName, String teacherId);
}
