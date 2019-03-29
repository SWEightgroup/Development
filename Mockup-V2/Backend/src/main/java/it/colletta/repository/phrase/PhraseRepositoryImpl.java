package it.colletta.repository.phrase;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.repository.phrase.PhraseCustomQueryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

public class PhraseRepositoryImpl implements PhraseCustomQueryInterface {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<PhraseModel> findAllByAuthor(String authorId) {
        Query query = new Query(Criteria.where("solutions.authorId").is(authorId));
        final List<PhraseModel> phraseModels = mongoTemplate.find(query, PhraseModel.class);
        //TODO eliminare informazioni aggiuntive
        return phraseModels;
    }

    @Override
    public List<SolutionModel> findAllSolutionsByAuthor(String authorId) {
        Query query = new Query(Criteria.where("solutions.authorId").is(authorId));
        query.fields().include("solutions");
        return mongoTemplate.find(query, SolutionModel.class);
    }
}
