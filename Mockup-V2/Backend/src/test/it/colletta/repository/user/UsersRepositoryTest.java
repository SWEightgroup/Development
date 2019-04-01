package it.colletta.repository.user;

import it.colletta.SweightApplication;
import it.colletta.model.UserModel;
import it.colletta.repository.config.MongoConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MongoConfig.class})
public class UsersRepositoryTest {

    /*
    @Autowired
    private UsersRepository userRepository;



    */

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() throws Exception {

        /*
        userRepository = new ??

        userRepository.set(mongoTemplate);
         */

        UserModel user = UserModel.builder()
                .firstName("Enrico")
                .email("a@a.it")
                .build();

        //mongoTemplate.save(user);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {

    }

}