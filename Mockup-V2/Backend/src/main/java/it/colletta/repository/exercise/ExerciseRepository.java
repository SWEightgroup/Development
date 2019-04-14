package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.ExerciseModel;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository<ExerciseModel, String> {

  @Override
  Iterable<ExerciseModel> findAllById(Iterable<String> ids);

  Page<ExerciseModel> findAllByPaged(Pageable page, Iterable<String> ids);

  @Override
  Optional<ExerciseModel> findById(String id);

  /**
   * @param newAuthorName
   * @param teacherId
   * @return
   */
  UpdateResult modifyAuthorName(String newAuthorName, String teacherId);
}
