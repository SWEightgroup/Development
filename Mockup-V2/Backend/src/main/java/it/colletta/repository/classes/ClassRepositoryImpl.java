package it.colletta.repository.classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class ClassRepositoryImpl implements ClassCustomQueryInterface {

    MongoTemplate mongoTemplate;

    @Autowired
    public ClassRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
