package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;

import it.colletta.model.ExerciseModel;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExerciseRepository extends MongoRepository<ExerciseModel, String> {

  @Override
  Iterable<ExerciseModel> findAllById(Iterable<String> ids);

  @Override
  Optional<ExerciseModel> findById(String id);

  /**
   * 
   * @param newAuthorName
   * @param teacherId
   * @return
   */
  UpdateResult modifyAuthorName(String newAuthorName, String teacherId);
}
