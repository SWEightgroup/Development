package it.colletta.repository.phrase;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhraseRepository
    extends MongoRepository<PhraseModel, String>, PhraseCustomQueryInterface {


  /**
   * @param phraseId the unique Id of the phrase
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
