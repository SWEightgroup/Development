package it.colletta.repository.phrase;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import java.util.List;

// interface for custom query on phrases collections

public interface PhraseCustomQueryInterface {

  /**
   * @param authorId
   * @return
   */
  List<PhraseModel> findAllByAuthor(String authorId);

  /**
   * @param authorId
   * @return
   */
  List<SolutionModel> findAllSolutionsByAuthor(String authorId);

  /**
   * @param solutionModels
   * @return
   */
  UpdateResult increaseReliability(SolutionModel solutionModels);

  /**
   * @param phraseId
   * @param solutionId
   * @return
   */
  SolutionModel getSolution(String phraseId, String solutionId);
}
