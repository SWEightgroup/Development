package it.colletta.repository.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import it.colletta.model.UserModel;
import it.colletta.repository.config.MongoConfig;
import it.colletta.security.Role;
import java.util.List;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MongoConfig.class})
public class UsersRepositoryImplTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  private UsersRepositoryImpl usersRepository;

  private UserModel testUser1;
  private UserModel testUser2;

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

    UserModel user1 = UserModel.builder().id("1").firstName("Enrico").email("a@a.it").enabled(false)
        .build();
    UserModel user2 = UserModel.builder().id("2").firstName("Francesco").email("b@b.it")
        .enabled(false).role(Role.ADMIN).build();

    testUser1 = mongoTemplate.save(user1);
    testUser2 = mongoTemplate.save(user2);
  }

  @Test
  public void updateActivateFlagOnly() {

    usersRepository.updateActivateFlagOnly(testUser1.getId());
    Query query = new Query(Criteria.where("_id").is(testUser1.getId()));
    UserModel updatedUser = mongoTemplate.findOne(query, UserModel.class);

    assertNotNull(updatedUser);
    assertTrue(updatedUser.isEnabled());
  }

  @Test
  public void getAllUsers() {

    List<UserModel> users = usersRepository.getAllUsers();

    assertEquals(users.size(), 1);
    assertEquals(users.get(0).getId(), "1");
  }
}
