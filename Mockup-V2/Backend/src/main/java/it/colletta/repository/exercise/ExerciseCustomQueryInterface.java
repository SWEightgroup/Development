package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;

import it.colletta.model.ExerciseModel;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExerciseCustomQueryInterface {

  /**
   *
   */
  UpdateResult modifyAuthorName(final String newAuthorName, String authorId);

  Page<ExerciseModel> findAllByIdPaged(Pageable page, List<ObjectId> ids);

  Page<ExerciseModel> findAllByAuthorIdPaged(Pageable page, String id);
}
