package it.colletta.repository.solution;

import com.mongodb.client.result.UpdateResult;

import it.colletta.repository.solution.SolutionCustomQueryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import it.colletta.model.SolutionModel;

@Component
public class SolutionRepositoryImpl implements SolutionCustomQueryInterface {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public Long increaseAffidability(SolutionModel solution) {
        Query select = Query.query(Criteria.where("solutionText").is(solution.getSolutionText()));
        Update update = new Update();
        Update affidabilty = update.inc("affidabilty", 1);
        UpdateResult updateResult = mongoTemplate.updateMulti(select, affidabilty, SolutionModel.class);
        return updateResult.getModifiedCount();
    }

}