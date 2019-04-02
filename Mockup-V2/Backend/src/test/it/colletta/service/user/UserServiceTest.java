package it.colletta.service.user;

import it.colletta.model.UserModel;
import it.colletta.repository.config.MongoConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MongoConfig.class})
public class UserServiceTest {

    /*
    @Autowired
    private UserService userService;

    private UserModel testUser;


    @Before
    public void setUp() throws Exception {


        testUser = UserModel.builder()
                .email("test@test.it")
                .password("test")
                .build();


    }
    */
    @Test
    public void addUser() {

    }

    @Test
    public void findById() {
    }

    @Test
    public void getUserInfo() {
    }

    @Test
    public void activateUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void addExerciseItem() {
    }
}