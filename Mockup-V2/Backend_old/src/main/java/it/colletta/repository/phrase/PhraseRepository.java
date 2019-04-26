package it.colletta.repository.phrase;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseRepository
    extends MongoRepository<PhraseModel, String>, PhraseCustomQueryInterface {


  /**
   * @param phraseId TODO
   */
  @Override
  void deleteById(final String phraseId);

  /**
   * @return Optional<PhraseModel>
   */
  @Override
  Iterable<PhraseModel> findAllById(Iterable<String> ids);

  /**
   * @paramauthorId
   */
  List<PhraseModel> findAllByAuthor(final String authorId);

  /**
   * @paramauthorId
   */
  List<SolutionModel> findAllSolutionsByAuthor(final String authorId);


  Optional<PhraseModel> findPhraseModelByPhraseTextIs(final String textToCompare);

}
