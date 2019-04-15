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
  List<PhraseModel> findAllByAuthor(final String authorId);

  /**
   * @param authorId
   * @return
   */
  List<SolutionModel> findAllSolutionsByAuthor(final String authorId);

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
  SolutionModel getSolution(final String phraseId, String solutionId);
}
