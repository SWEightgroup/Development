package it.colletta.repository.phrase;

import com.mongodb.client.result.UpdateResult;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.helper.FilterHelper;

import org.bson.Document;

import java.util.List;

// interface for custom query on phrases collections

public interface PhraseCustomQueryInterface {

  /**
   *
   */
  List<PhraseModel> findAllByAuthor(final String authorId);

  /**
   * findAllSolutionsByAuthor.
   * 
   * @param authorId Authorid
   * @return All solution
   */
  List<SolutionModel> findAllSolutionsByAuthor(final String authorId);

  /**
   *
   */
  UpdateResult increaseReliability(SolutionModel solutionModels);

  SolutionModel getSolution(final String phraseId, String solutionId);

  List<Document> findAllPhrases();

  List<Document> findAllPhrasesWithFilter(FilterHelper filter);
}
