package it.colletta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import it.colletta.model.Correction;
import it.colletta.model.Phrase;

@Repository
public interface PhraseRepository extends MongoRepository<Phrase, String> {

}
