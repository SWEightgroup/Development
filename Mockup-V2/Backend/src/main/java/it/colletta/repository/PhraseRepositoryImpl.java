package it.colletta.repository;

import it.colletta.model.PhraseModel;
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
        return phraseModels;
    }
}
