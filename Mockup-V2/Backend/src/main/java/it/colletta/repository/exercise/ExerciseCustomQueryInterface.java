package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;

public interface ExerciseCustomQueryInterface {
  UpdateResult modifyAuthorName(String newAuthorName, String authorId);
}
