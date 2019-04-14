package it.colletta.service.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import it.colletta.model.ExerciseModel;
import it.colletta.model.UserModel;
import it.colletta.repository.user.UsersRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
    properties =
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class UserServiceTest {

  @MockBean private UsersRepository usersRepository;

  private UserService userService;

  private UserModel testUser;

  @Before
  public void setUp() throws Exception {

    userService = new UserService(usersRepository, new BCryptPasswordEncoder());

    testUser =
        UserModel.builder()
            .id("1")
            .email("test@test.it")
            .password("12345")
            .firstName("Tom")
            .build();
  }

  @Test
  public void addUser() {

    Mockito.when(usersRepository.save(testUser)).thenReturn(testUser);

    UserModel addedUser = userService.addUser(testUser);
    assertEquals(addedUser.getId(), "1");
    assertNull(addedUser.getPassword());
  }

  @Test
  public void findById() {

    Mockito.when(usersRepository.findById("1")).thenReturn(Optional.of(testUser));

    Optional<UserModel> userOptional = userService.findById("1");
    if (userOptional.isPresent()) assertEquals(userOptional.get().getId(), "1");
    else Assert.fail();
  }

  @Test
  public void getUserInfo() {

    Mockito.when(usersRepository.findById("1")).thenReturn(Optional.of(testUser));

    UserModel addedUser = userService.getUserInfo("1");

    assertEquals(addedUser.getId(), "1");
  }

  @Test(expected = UsernameNotFoundException.class)
  public void getUserInfoWithWrongId() {
    Mockito.when(usersRepository.findById("2")).thenReturn(Optional.empty());

    userService.getUserInfo("2");
  }

  @Test
  public void updateUser() {}

  @Test
  public void addExerciseItem() {

    ArrayList<UserModel> users = new ArrayList<>();
    users.add(UserModel.builder().id("2").build());
    users.add(UserModel.builder().id("3").build());

    ArrayList<String> userIds = new ArrayList<>();
    userIds.add("2");
    userIds.add("3");

    ExerciseModel exercise = ExerciseModel.builder().id("11").build();

    Mockito.when(usersRepository.findAllById(userIds)).thenReturn(users);

    userService.addExerciseItem(userIds, exercise);

    // Capture the users that are passed to saveAll
    ArgumentCaptor<ArrayList> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);
    Mockito.verify(usersRepository).saveAll(argumentCaptor.capture());
    ArrayList<UserModel> insertedUsers = argumentCaptor.getValue();

    // Check that the first user has the inserted exercise in exercisesToDo
    assertEquals(insertedUsers.get(0).getExercisesToDo().size(), 1);
    assertEquals(insertedUsers.get(0).getExercisesToDo().get(0), "11");

    // Check that the second user has the inserted exercise in exercisesToDo
    assertEquals(insertedUsers.get(1).getExercisesToDo().size(), 1);
    assertEquals(insertedUsers.get(1).getExercisesToDo().get(0), "11");
  }
}
