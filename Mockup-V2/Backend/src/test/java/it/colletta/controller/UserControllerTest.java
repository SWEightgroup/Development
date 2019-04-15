package it.colletta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.UserModel;
import it.colletta.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

  private MongoTemplate mongoTemplate;
  private MockMvc mvc;
  private String jwt;
  private ObjectMapper mapper;
  private String userToken =
          "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmcmFuY2VzY29vQGdtYWlsLmNvbSIsImV4cCI6MTU1NjIyNTQ2MiwianRpIjoiNWNiNDc1MzEwNjQ2YjMxMDVmOGY5YjA0In0.GI3yFJgTB7eccrBed3LEHpQqb8kx0iNgvxPMzaDPP-FWddBUbl4-8JYVoy7H0ZCk6XhHXJY4PvKCcvMxMbua8A";

  @Mock
  UserService userService;

  @Before
  public void setup() {
    /*jwt = ("Bearer") + JWT.create()
            .withJWTId("test@test.it")
            .withSubject("test")
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes())); */

    mapper = new ObjectMapper();

    final UserController userController = new UserController();
    userController.setUserService(userService);

    mvc = MockMvcBuilders.standaloneSetup(userController)
            .alwaysExpect(status().isOk())
            .build();
  }



  /*
  @Test
  public void signUpTest() {
    try {
      UserModel fakeTestUser =
              UserModel.builder()
                      .email("prova@prova.it")
                      .firstName("gianni")
                      .lastName("pinotto")
                      .password("Provaprova")
                      .language("it")
                      .role("ROLE_STUDENT")
                      .dateOfBirth(new Date(02-04-2013))
                      .build();

      String jsonUser = mapper.writeValueAsString(fakeTestUser);
      mvc.perform(MockMvcRequestBuilders.post("localhost:8081/login")
              .content(jsonUser));
              .param("username","prova@prova.it")
              .param("firstName","gianni")
              .param("lastName","pinotto")
              .param("password", "Provaprova")
              .param("language","it")
              .param("role","ROLE_STUDENT")
              .param("dateOfBirth","2018-01-01"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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
      mvc.perform(MockMvcRequestBuilders.get("/users/developer/get-all-disabled").header("Authorization", userToken));
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
