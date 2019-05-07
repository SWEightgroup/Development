package it.colletta.service.user;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;

import com.auth0.jwt.JWT;
import it.colletta.model.ExerciseModel;
import it.colletta.model.StudentModel;
import it.colletta.model.UserModel;
import it.colletta.repository.user.UsersRepository;
import it.colletta.security.Role;

import java.util.ArrayList;
import java.util.Date;
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

  private  String token;

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

    token = JWT.create().withJWTId(user.getId()).withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));
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

    Mockito.when(usersRepository.findByEmail(anyString())).thenReturn(user);
    Mockito.when(usersRepository.save(any(UserModel.class))).thenReturn(user);
    UserModel myuser = userService.updateUser(user,token);

    assertEquals(myuser.getUsername(), user.getUsername());
    assertEquals(myuser.getRole(), user.getRole());
  }

  @Test
  public void getAllListUser(){

    List<String> StudentIds = new ArrayList<>();
    StudentIds.add("104");
    List<UserModel> listusermodel = new ArrayList<>();
    listusermodel.add(user);

    Mockito.when(usersRepository.findAllByList(StudentIds)).thenReturn(listusermodel);

    List<UserModel> Mylistusermodel = userService.getAllListUser(StudentIds);
    assertEquals(Mylistusermodel.get(0).getUsername(), user.getUsername());
    assertEquals(Mylistusermodel.get(0).getRole(), user.getRole());

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
