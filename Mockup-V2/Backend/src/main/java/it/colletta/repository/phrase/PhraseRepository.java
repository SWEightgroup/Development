package it.colletta.repository.phrase;

import java.util.List;
import java.util.Optional;
import it.colletta.model.SolutionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import it.colletta.model.PhraseModel;
import it.colletta.repository.phrase.PhraseCustomQueryInterface;

@Repository
public interface PhraseRepository extends MongoRepository<PhraseModel, String>, PhraseCustomQueryInterface {

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

    /**
     * @param TODO
     * @return List<PhraseModel>
     */
    @Override
    List<PhraseModel> findAll();

    /**
     * @param textToCompare
     * @return Long
     */
    @Query(value = "{'phraseText': {$regex: ?0, $options: 'i'}}", count = true)
    Long countPhrasesWithText(String textToCompare);
    // WE CAN DO BETTER cit.

    /**
     * @param ids
     * @return Optional<PhraseModel>
     */
    @Override
    Iterable<PhraseModel> findAllById(Iterable<String> ids);

    List<PhraseModel> findAllByAuthor(String  authorId);

    List<SolutionModel> findAllSolutionsByAuthor(String authorId);

    /**
     * @param phraseTexr
     * @return Optional<PhraseModel>
     */
    @Query(value = "{'phraseText': {$regex: ?0, $options: 'i'}}")
    Optional<PhraseModel> getPhraseWithText(String textToCompare);

    // @Query(value = "{'id': { '$in' : 'ids':?#{[0]}}}")
    // List<PhraseModel> findAllPhrasesByIds(List<String> ids);
}
