package it.colletta.service.user;

import it.colletta.model.ExerciseModel;
import it.colletta.model.UserModel;
import it.colletta.repository.user.UsersRepository;
import it.colletta.security.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource(
        properties =
                "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class UserServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserService userService;

    private UserModel testUser;
    private UserModel testTeacher;
    private UserModel testAdmin;
    private ExerciseModel testExercise;

    @Before
    public void setUp() throws Exception {

        ArrayList<String> exercisesToDo = new ArrayList<>();
        exercisesToDo.add("333");
        exercisesToDo.add("334");
        exercisesToDo.add("335");

        ArrayList<String> exercisesDone = new ArrayList<>();
        exercisesDone.add("336");
        exercisesDone.add("337");

        testUser =
                UserModel.builder()
                        .id("1")
                        .email("test@test.it")
                        .firstName("Tom")
                        .exercisesToDo(exercisesToDo)
                        .exercisesDone(exercisesDone)
                        .role(Role.STUDENT)
                        .build();

        testTeacher =
                UserModel.builder()
                        .id("2")
                        .email("test@teacher.it")
                        .role(Role.TEACHER)
                        .build();

        testAdmin =
                UserModel.builder()
                        .id("3")
                        .email("test@admin.it")
                        .role(Role.ADMIN)
                        .build();

        testExercise =
                ExerciseModel.builder()
                        .id("335")
                        .build();

        Mockito.when(usersRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));
        Mockito.when(usersRepository.findById(testTeacher.getId())).thenReturn(Optional.of(testTeacher));
        Mockito.when(usersRepository.findById(testAdmin.getId())).thenReturn(Optional.of(testAdmin));
    }

  /*@Test
  public void addUser() {

    Mockito.when(usersRepository.save(testUser)).thenReturn(testUser);

    UserModel addedUser = userService.addUser(testUser);
    assertEquals(addedUser.getId(), "1");
    assertNull(addedUser.getPassword());
  }*/

    @Test
    public void findById() {

        userService.findById("1");

        Mockito.verify(usersRepository).findById("1");
    }

    @Test
    public void getUserInfo() {

        UserModel addedUser = userService.getUserInfo(testUser.getId());

        assertEquals(addedUser.getId(), testUser.getId());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void getUserInfoWithWrongId() {

        Mockito.when(usersRepository.findById(testTeacher.getId())).thenReturn(Optional.empty());

        userService.getUserInfo(testTeacher.getId());
    }

    @Test
    public void updateUser() {

    }

    @Test
    public void addExerciseItem() {

        ArrayList<UserModel> users = new ArrayList<>();
        users.add(UserModel.builder().id("2").exercisesToDo(new ArrayList<>()).build());
        users.add(UserModel.builder().id("3").exercisesToDo(new ArrayList<>()).build());

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

    @Test
    public void activateUser() {

        userService.activateUser(testUser.getId());

        Mockito.verify(usersRepository).updateActivateFlagOnly(testUser.getId());
    }

    @Test
    public void deleteUser() {

        userService.deleteUser(testUser.getId());

        Mockito.verify(usersRepository).delete(testUser);
    }

    @Test
    public void findByEmail() {

        userService.findByEmail("test@test.it");

        Mockito.verify(usersRepository).findByEmail("test@test.it");
    }

    @Test
    public void getAllExerciseToDo() {

        List<String> exercisesToDo = userService.getAllExerciseToDo(testUser.getId());

        assertEquals(exercisesToDo.size(), 3);
    }

    @Test
    public void getAllStudents() {

        userService.getAllStudents();
        Mockito.verify(usersRepository).findAllStudents();
    }

    @Test
    public void getAllUsers() {

        userService.getAllUsers();
        Mockito.verify(usersRepository).getAllUsers();
    }

    @Test
    public void deleteExerciseAssigned() {

        userService.deleteExerciseAssigned("333", testTeacher.getId());
        Mockito.verify(usersRepository).deleteFromExerciseToDo("333");
    }

    @Test
    public void getAllExerciseDone() {

        List<String> exercisesDone = userService.getAllExerciseDone(testUser.getId());

        assertEquals(exercisesDone.size(), 2);
    }

    @Test
    public void exerciseCompleted() {

        userService.exerciseCompleted(testUser.getId(), testExercise);

        // Capture the user that is saved to the repository
        ArgumentCaptor<UserModel> argumentCaptor = ArgumentCaptor.forClass(UserModel.class);
        Mockito.verify(usersRepository).save(argumentCaptor.capture());
        UserModel savedUser = argumentCaptor.getValue();

        assertFalse(savedUser.getExercisesToDo().contains("335"));
        assertTrue(savedUser.getExercisesDone().contains("335"));
    }

    @Test
    public void getAllDevelopersToEnable() {

        userService.getAllDevelopmentToEnable(testAdmin.getId());

        Mockito.verify(usersRepository).findAllDeveloperDisabled();
    }
}
