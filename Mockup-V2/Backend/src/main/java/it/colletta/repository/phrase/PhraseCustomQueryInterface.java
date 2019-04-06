package it.colletta.repository.phrase;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import java.util.List;

// interface for custom query on phrases collections

public interface PhraseCustomQueryInterface {
  List<PhraseModel> findAllByAuthor(String authorId);

  List<SolutionModel> findAllSolutionsByAuthor(String authorId);

  UpdateResult increaseReliability(SolutionModel solutionModels);

  SolutionModel getSolution(String phraseId, String solutionId);
}
