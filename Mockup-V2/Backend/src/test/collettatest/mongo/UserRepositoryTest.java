package collettatest.mongo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import it.colletta.model.UserModel;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.junit.Assert.*;
@DataMongoTest
public class UserRepositoryTest {
    @Autowired MongoTemplate mongoTemplate;
    @Test
    public void insertNewUser() {
        UserModel userModel = new UserModel();
        userModel.setEmail("aaa@aaa.it");
        userModel.setPassword("password");
        UserModel userInserted = mongoTemplate.save(userModel, "users");
        assertEquals(userModel, userInserted);
    }
}