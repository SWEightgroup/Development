package it.colletta.controller;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.UserModel;
import it.colletta.service.user.UserService;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

  @Mock
  UserService userService;
  @InjectMocks
  UserController userController;
  private MockMvc mvc;
  private String userToken;
  private ObjectMapper mapper;
  private UserModel fakeTestUser;

  @Before
  public void setup() {
    userToken = ("Bearer") + JWT.create()
        .withJWTId("test@test.it")
        .withSubject("test")
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(HMAC512(SECRET.getBytes()));

    fakeTestUser = UserModel.builder()
        .firstName("fransisco")
        .lastName("corti")
        .language("it")
        .role("ROLE_TEACHER")
        .build();

    mapper = new ObjectMapper();
    mvc = MockMvcBuilders.standaloneSetup(userController)
        //.alwaysExpect(status().isOk())
        .build();
  }

  /*@Test
  public void userTryToModify(){
    try{
      String jsonFakeTestUser = mapper.writeValueAsString(fakeTestUser);
      mvc.perform(MockMvcRequestBuilders.put("/users/modify")
              .header("Authorization", userToken)
              .content(jsonFakeTestUser)
              .contentType(MediaType.APPLICATION_JSON_VALUE)
              .accept(MediaType.APPLICATION_JSON)
              .characterEncoding("UTF-8"));
    }catch (Exception e){
      e.printStackTrace();
    }
  } */

  @Test
  public void checkTokenUserControllerTest() {
    try {
      mvc.perform(MockMvcRequestBuilders.put("/users/get-info")
          .contentType(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isMethodNotAllowed());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void checkAdminCallWithoutToken() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/users/admin/get-all-disabled"))
          .andExpect(status().isBadRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void activateDeveloperTest() {
    try {
      mvc.perform(MockMvcRequestBuilders.put("/users/admin/activate-user/developerId")
          .contentType(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void deleteUserTest() {
    try {
      mvc.perform(MockMvcRequestBuilders.delete("/admin/delete-user/userToDeleteId")
          .contentType(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getAllDeveloperDisabled() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/users/admin/get-all-disabled")
          .header("Authorization", userToken))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getAllUsers() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/users/admin/get-all-user")
          .header("Authorization", userToken))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void userGetInfo() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/users/get-info")
          .header("Authorization", userToken))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getAllUser() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/users/get-students")
          .header("Authorization", userToken))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
