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
/*
    Mockito.when(usersRepository.findByEmail(anyString())).thenReturn(user);
    Mockito.when(usersRepository.save(any(UserModel.class))).thenReturn(user);
    UserModel myuser = userService.updateUser(user,anyString());

 */

  }

  @Test
  public void getAllListUser(){
    /*
    List<String> StudentIds = new ArrayList<>();
    StudentIds.add("104");
    List<UserModel> listusermodel = new ArrayList<>();
    listusermodel.add(user);

    Mockito.when(usersRepository.findAllByList(StudentIds)).thenReturn(listusermodel);

    List<UserModel> Mylistusermodel = userService.getAllListUser(StudentIds);
  */
  }

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


  @Test
  public void getAllUsers() {

    userService.getAllUsers();
    Mockito.verify(usersRepository).getAllUsers();
  }


  @Test
  public void getAllDevelopersToEnable() {

    userService.getAllDevelopmentToEnable(user.getId());

    Mockito.verify(usersRepository).findAllDeveloperDisabled();
  }


}
