package it.colletta.controller;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.ClassModel;
import it.colletta.repository.classes.ClassRepository;
import it.colletta.service.classes.ClassService;
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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ClassControllerTest {


  private MockMvc mvc;
  private String userToken;
  private ObjectMapper mapper;
  private ClassModel classModelTest;

  @Mock
  ClassService classService;

  @Mock
  ClassRepository classRepository;

  @InjectMocks
  ClassController classController;

  @Before
  public void setUp(){
    userToken = ("Bearer") + JWT.create().withJWTId("test@test.it").withSubject("test")
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));

    mapper = new ObjectMapper();
    mvc = MockMvcBuilders.standaloneSetup(classController)
            // .alwaysExpect(status().isOk())
            .build();

    classModelTest = ClassModel.builder()
            .id("1")
            .name("test-class")
            .teacher("FMag")
            .studentsId(Arrays.asList("1","2","3"))
            .build();

  }


  @Test
  public void checkCreateNewClassTest() {
    try {
      String jsonClassModelTest = mapper.writeValueAsString(classModelTest);
      mvc.perform(MockMvcRequestBuilders.post("/class/create-class")
              .header("Authorization", userToken)
              .content(jsonClassModelTest)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void insertClassStudentTest(){
    try{
      mvc.perform(MockMvcRequestBuilders.post("/class/add-student-class")
              .header("Authorization", userToken)
              .param("student-id","123")
              .param("class-id","123")
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @Test
  public void renameClassTest(){
    try{
      String jsonClassModelTest = mapper.writeValueAsString(classModelTest);
      mvc.perform(MockMvcRequestBuilders.put("/class/rename-class")
              .header("Authorization", userToken)
              .content(jsonClassModelTest)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @Test
  public void removeStudentTest(){
    try{
      mvc.perform(MockMvcRequestBuilders.delete("/class/remove-student-class")
              .header("Authorization", userToken)
              .param("student-id","123")
              .param("class-id", "123")
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @Test
  public void deleteClassTest(){
    try{
      mvc.perform(MockMvcRequestBuilders.delete("/class/delete-class")
              .header("Authorization", userToken)
              .param("class-id","123")
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @Test
  public void getAllTeacherClassesTest(){
    try{
      mvc.perform(MockMvcRequestBuilders.get("/class/get-all-classes")
              .header("Authorization", userToken)
              .param("teacher-id","123")
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e){
      e.printStackTrace();
    }
  }

}