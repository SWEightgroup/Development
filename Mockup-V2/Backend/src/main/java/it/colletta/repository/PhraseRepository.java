package it.colletta.repository;

import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import it.colletta.model.PhraseModel;
import it.colletta.repository.PhraseCustomQueryInterface;

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
    public Long countPhrasesWithText(String textToCompare);
    // WE CAN DO BETTER cit.

    /**
     * @param ids
     * @return Optional<PhraseModel>
     */
    @Override
    public Iterable<PhraseModel> findAllById(Iterable<String> ids);


    public List<PhraseModel> findAllByAuthor(String  authorId);
    /**
     * @param phraseTexr
     * @return Optional<PhraseModel>
     */
    @Query(value = "{'phraseText': {$regex: ?0, $options: 'i'}}")
    public Optional<PhraseModel> getPhraseWithText(String textToCompare);
    

}
