package it.colletta.repository.phrase;

import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import java.util.List;

import it.colletta.model.helper.FilterHelper;
import org.bson.Document;

// interface for custom query on phrases collections

public interface PhraseCustomQueryInterface {

  /**
   *
   */
  List<PhraseModel> findAllByAuthor(final String authorId);

  /**
   *
   */
  List<SolutionModel> findAllSolutionsByAuthor(final String authorId);

  /**
   *
   */
  UpdateResult increaseReliability(SolutionModel solutionModels);


  SolutionModel getSolution(final String phraseId, String solutionId);


  FindIterable<Document> findAllPhrasesAsIterable();

    FindIterable<Document> findAllPhrasesWithFilter(FilterHelper filter);
}
