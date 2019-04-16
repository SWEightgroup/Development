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
  UpdateResult modifyAuthorName(final String newAuthorName, String authorId);

  Page<ExerciseModel> findAllByIdPaged(Pageable page, Iterable<String> ids);
}
