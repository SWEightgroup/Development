package it.colletta.repository.exercise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import it.colletta.model.ExerciseModel;

public class ExerciseRepositoryImpl implements ExerciseCustomQueryInterface {
    @Autowired MongoTemplate mongoTemplate;
    @Override
    public List<ExerciseModel> findAllPublicExercises(List<String> exerciseToExclude) {
        Query query = new Query(Criteria.where("id").nin(exerciseToExclude).and("visibility").is(true));
        return mongoTemplate.find(query, ExerciseModel.class);    
    }

}
