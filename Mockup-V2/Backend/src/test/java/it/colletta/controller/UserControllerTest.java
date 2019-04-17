package it.colletta.controller;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Date;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

  private MockMvc mvc;
  private String userToken;
  private ObjectMapper mapper;

  @Mock
  UserService userService;

  @InjectMocks
  UserController userController;

  @Before
  public void setup() {
    userToken = ("Bearer") + JWT.create()
                    .withJWTId("test@test.it")
                    .withSubject("test")
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(HMAC512(SECRET.getBytes()));
    mapper = new ObjectMapper();
    mvc = MockMvcBuilders.standaloneSetup(userController)
            .alwaysExpect(status().isOk())
            .build();
  }




  /*
  @Test
  public void userTryToModify(){
    try{
      UserModel fakeTestUser =
              UserModel.builder()
                      .firstName("fransisco")
                      .lastName("corti")
                      .language("it")
                      .build();
      String jsonUser = mapper.writeValueAsString(fakeTestUser);
      mvc.perform(MockMvcRequestBuilders.put("/users/modify")
              .header("Authorization", userToken)
              .param("username","prova@prova.it")
              .param("firstName","gianni")
              .param("lastName","pinotto")
              .param("password", "Provaprova")
              .param("language","it")
              .param("role","ROLE_STUDENT")
              .param("dateOfBirth","2018-01-01"));
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  @Test
  public void testLogin(){
    try{
      mvc.perform(MockMvcRequestBuilders.post("localhost:8081/login")
          .param("username","francesco@gmail.com")
          .param("password","Provaprova"));
    }catch(Exception e){
      e.printStackTrace();
    }

  }
 */

  @Test
  public void getAllDeveloperDisabled() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/users/admin/get-all-disabled").header("Authorization", userToken));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getAllUsers() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/users/admin/get-all-user").header("Authorization", userToken));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void userGetInfo() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/users/get-info").header("Authorization", userToken));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getAllUser() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/users/get-students").header("Authorization", userToken));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
