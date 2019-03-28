package it.colletta.repository.classes;

import it.colletta.model.ClassModel;
import it.colletta.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.ArrayList;
import java.util.List;

public class ClassRepositoryImpl implements ClassCustomQueryInterface {

    MongoTemplate mongoTemplate;

    @Autowired
    public ClassRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
