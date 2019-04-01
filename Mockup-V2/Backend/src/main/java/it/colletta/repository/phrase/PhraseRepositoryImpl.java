package it.colletta.repository.phrase;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.repository.phrase.PhraseCustomQueryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import com.mongodb.WriteResult;
import com.mongodb.client.result.UpdateResult;

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
    @Override
    public void increaseReliability(SolutionModel solutionModel) {
        UpdateResult wr = mongoTemplate.updateMulti(
                new Query(Criteria.where("solutions.solutionText").is(solutionModel.getSolutionText())),
        new Update().inc("solutions.$.reliability", 1), PhraseModel.class);
        
    }
}



