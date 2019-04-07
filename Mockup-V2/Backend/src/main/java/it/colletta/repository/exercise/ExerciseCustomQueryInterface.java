package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;

public interface ExerciseCustomQueryInterface {

  /**
   * 
   * @param newAuthorName
   * @param authorId
   * @return
   */
  UpdateResult modifyAuthorName(String newAuthorName, String authorId);
}
