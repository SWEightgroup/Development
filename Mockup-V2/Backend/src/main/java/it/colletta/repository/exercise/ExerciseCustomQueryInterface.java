package it.colletta.repository.exercise;

import java.util.List;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.ExerciseModel;
import it.colletta.model.UserModel;

public interface ExerciseCustomQueryInterface {
  UpdateResult modifyAuthorName(String newAuthorName, String authorId);
}
