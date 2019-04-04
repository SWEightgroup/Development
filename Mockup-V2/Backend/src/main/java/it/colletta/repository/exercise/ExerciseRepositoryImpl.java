package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.ExerciseModel;
import it.colletta.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class ExerciseRepositoryImpl implements ExerciseCustomQueryInterface {
    private MongoTemplate mongoTemplate;

    @Autowired
    public ExerciseRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public UpdateResult modifyAuthorName(String newAuthorName, String authorId) {
        Query exerciseToUpdate = new Query(Criteria.where("authorId").is(authorId));
        Update modify = new Update();
        modify.set("authorName", newAuthorName);
        return mongoTemplate.updateMulti(exerciseToUpdate, modify, ExerciseModel.class);
    }

}
