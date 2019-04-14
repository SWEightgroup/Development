package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.ExerciseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExerciseCustomQueryInterface {

  /**
   * @param newAuthorName
   * @param authorId
   * @return
   */
  UpdateResult modifyAuthorName(String newAuthorName, String authorId);


  Page<ExerciseModel> findAllByPaged(Pageable page, Iterable<String> ids);
}
