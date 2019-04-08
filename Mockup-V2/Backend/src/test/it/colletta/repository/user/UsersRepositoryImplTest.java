package it.colletta.repository.user;

import static org.junit.Assert.*;

import it.colletta.model.UserModel;
import it.colletta.repository.config.MongoConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MongoConfig.class})
public class UsersRepositoryImplTest {

  @Autowired private MongoTemplate mongoTemplate;

  private UsersRepositoryImpl usersRepository;

  private UserModel testUser;

  @Before
  public void setUp() throws Exception {

    usersRepository = new UsersRepositoryImpl(mongoTemplate);

    populateDatabase();
  }

  @After
  public void tearDown() throws Exception {

    mongoTemplate.dropCollection("users");
  }

  private void populateDatabase() {

    UserModel user = UserModel.builder().firstName("Enrico").email("a@a.it").enabled(false).build();

    testUser = mongoTemplate.save(user);
  }

  @Test
  public void updateActivateFlagOnly() {

    usersRepository.updateActivateFlagOnly(testUser.getId());
    Query query = new Query(Criteria.where("_id").is(testUser.getId()));
    UserModel updatedUser = mongoTemplate.findOne(query, UserModel.class);

        usersRepository.updateActivateFlagOnly(testUser.getId());
        Query query = new Query(Criteria.where("_id").is(testUser.getId()));
        UserModel updatedUser = mongoTemplate.findOne(query, UserModel.class);

        assertNotNull(updatedUser);
        assertTrue(updatedUser.isEnabled());
    }
}
