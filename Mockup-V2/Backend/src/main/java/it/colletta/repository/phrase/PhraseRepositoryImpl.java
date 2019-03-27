package it.colletta.repository.phrase;

import it.colletta.model.PhraseModel;
import it.colletta.repository.phrase.PhraseCustomQueryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

public class PhraseRepositoryImpl implements PhraseCustomQueryInterface {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<PhraseModel> findAllByAuthor(String authorId) {
        Query query = new Query(Criteria.where("solutions.authorId").is(authorId));
        // mi ritorna tutti i documenti fatti da authorId, togli tutti i campi tranne textPhrase e
        // ritorni
        final List<PhraseModel> phraseModels = mongoTemplate.find(query, PhraseModel.class);

        return phraseModels;
    }
}
