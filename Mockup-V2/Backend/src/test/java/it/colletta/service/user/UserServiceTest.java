package it.colletta.service.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;

import it.colletta.model.ExerciseModel;
import it.colletta.model.StudentModel;
import it.colletta.model.UserModel;
import it.colletta.repository.user.UsersRepository;
import it.colletta.security.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  @Mock
  private UsersRepository usersRepository;

  @InjectMocks
  private UserService userService;

  private UserModel user;

  private ExerciseModel testExercise;

  private StudentModel studentModel;

  @Before
  public void setUp() throws Exception {
    user = new UserModel();
    user.setId("3");
    user.setEmail("test@admin.it");
    user.setRole(Role.ADMIN);

    List<String> TeacherIds = new ArrayList<>();
    TeacherIds.add("104");

    studentModel = new StudentModel();
    studentModel.setCurrentGoal(10);
    studentModel.setFavoriteTeacherIds(TeacherIds);

    Mockito.when(usersRepository.findById(anyString())).thenReturn(Optional.of(user));
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

    UserModel addedUser = userService.getUserInfo(user.getId());
    assertEquals(addedUser.getId(), user.getId());
  }

  @Test(expected = UsernameNotFoundException.class)
  public void getUserInfoWithWrongId() {

    Mockito.when(usersRepository.findById(anyString())).thenReturn(Optional.empty());

    userService.getUserInfo(user.getId());
  }

  @Test
  public void updateUser() {

  }

    /*@Test
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
    }*/

  @Test
  public void activateUser() {

    userService.activateUser(user.getId());

    Mockito.verify(usersRepository).updateActivateFlagOnly(user.getId());
  }

  @Test
  public void deleteUser() {

    userService.deleteUser(user.getId());

    Mockito.verify(usersRepository).delete(user);
  }

  @Test
  public void findByEmail() {

    userService.findByEmail("test@test.it");

    Mockito.verify(usersRepository).findByEmail("test@test.it");
  }

    /*@Test
    public void getAllExerciseToDo() {

        List<String> exercisesToDo = userService.getAllExerciseToDo(testUser.getId());

        assertEquals(exercisesToDo.size(), 3);
    }

    @Test
    public void getAllStudents() {

        userService.getAllStudents();
        Mockito.verify(usersRepository).findAllStudents();
    }*/

  @Test
  public void getAllUsers() {

    userService.getAllUsers();
    Mockito.verify(usersRepository).getAllUsers();
  }

    /*@Test
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

     */

  @Test
  public void getAllDevelopersToEnable() {

    userService.getAllDevelopmentToEnable(user.getId());

    Mockito.verify(usersRepository).findAllDeveloperDisabled();
  }


  @Test
  public void getFavoriteTeachers(){
    /*
    Mockito.when(usersRepository.findById(anyString())).thenReturn(Optional.of(studentModel));
    List<UserLighterHelper> userLighterHelpers = new ArrayList<>();
    userLighterHelpers = userService.getFavoriteTeachers(anyString());

     */
  }

  @Test
  public void modifyFavoriteTeachers(){
    /*
    List<String> TeacherIds = new ArrayList<>();
    TeacherIds.add("104");

    userService.modifyFavoriteTeachers(anyString(),TeacherIds);
    Mockito.verify(usersRepository).findById(anyString());
    Mockito.verify(usersRepository).save(any(StudentModel.class));

     */
  }

}
