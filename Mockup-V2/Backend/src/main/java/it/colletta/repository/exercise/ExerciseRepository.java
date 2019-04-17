package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.ExerciseModel;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExerciseRepository extends MongoRepository<ExerciseModel, String>,
    ExerciseCustomQueryInterface {

  @Override
  Iterable<ExerciseModel> findAllById(Iterable<String> ids);

    /**
   * @param newAuthorName
   * @param teacherId
   * @return
   */
  UpdateResult modifyAuthorName(final String newAuthorName, String teacherId);
}
