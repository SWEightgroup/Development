package it.colletta.repository;

import com.mongodb.client.result.UpdateResult;

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
    public void increaseAffidability(SolutionModel solution) {
        Query select = Query.query(Criteria.where("solutionText").is(solution.getSolutionText()));
        Update update = new Update();
        update.inc("affidabilty", 0.01);
        SolutionModel updateResult = mongoTemplate.findAndModify(select, update, SolutionModel.class);
    }

}