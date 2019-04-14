package it.colletta.repository.phrase;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhraseRepository
    extends MongoRepository<PhraseModel, String>, PhraseCustomQueryInterface {

  /**
   * @param phraseToDelete TODO
   * @return nothing
   */
  @Override
  void delete(PhraseModel phraseToDelete);

  /**
   * @param phraseId TODO
   * @return nothing
   */
  @Override
  void deleteById(String phraseId);

  /** @return List<PhraseModel> */
  @Override
  List<PhraseModel> findAll();

  /** @return Long */
  @Query(value = "{'phraseText': {$regex: ?0, $options: 'i'}}", count = true)
  Long countPhrasesWithText(String textToCompare);
  // WE CAN DO BETTER cit.

  /** @return Optional<PhraseModel> */
  @Override
  Iterable<PhraseModel> findAllById(Iterable<String> ids);

  /** @paramauthorId */
  List<PhraseModel> findAllByAuthor(String authorId);

  /** @paramauthorId */
  List<SolutionModel> findAllSolutionsByAuthor(String authorId);

  /**
   * @param textToCompare
   * @return Optional<PhraseModel>
   */
  @Query(value = "{'phraseText': {$regex: ?0, $options: 'i'}}")
  Optional<PhraseModel> getPhraseWithText(String textToCompare);

  /**
   * @param solutionIId
   * @return
   */
  @Query("{'phrases.solutions._id' : ?0} , {solutions: 1, id:0}")
  List<SolutionModel> getSolution(String solutionIId);
  // @Query(value = "{'id': { '$in' : 'ids':?#{[0]}}}")
  // List<PhraseModel> findAllPhrasesByIds(List<String> ids);
}
