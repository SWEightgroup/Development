package it.colletta.controller;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.ClassModel;
import it.colletta.model.helper.ClassHelper;
import it.colletta.model.helper.StudentClassHelper;
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
  private ClassHelper classHelperTest;
  private StudentClassHelper studentClassHelpertest;

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
            .build();

    classHelperTest = ClassHelper.builder()
            .teacherId("1")
            .name("theClass")
            .studentsId(Arrays.asList("1","2","3"))
            .build();
    studentClassHelpertest = StudentClassHelper.builder()
            .classId("1")
            .studentsId(Arrays.asList("1","2","3"))
            .name("className")
            .build();
  }

  @Test
  public void CreateNewClassTest() {
    try {
      String jsonClassHelperTest = mapper.writeValueAsString(classHelperTest);
      mvc.perform(MockMvcRequestBuilders.post("/class/create")
              .header("Authorization", userToken)
              .content(jsonClassHelperTest)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void CreateNewClassNullParameterTest() {
    try {
      ClassHelper classHelperWrong = ClassHelper.builder()
              .teacherId(null)
              .name(null)
              .studentsId(Arrays.asList("1","2","3"))
              .build();
      String jsonClassHelperTest = mapper.writeValueAsString(classHelperWrong);
      mvc.perform(MockMvcRequestBuilders.post("/class/create")
              .header("Authorization", userToken)
              .content(jsonClassHelperTest)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isBadRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void ModifyClassTest() {
    try {
      String jsonStudentHelperTest = mapper.writeValueAsString(studentClassHelpertest);
      mvc.perform(MockMvcRequestBuilders.put("/class/modify")
              .header("Authorization", userToken)
              .content(jsonStudentHelperTest)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void ModifyClassNullParameterTest() {
    try {
      StudentClassHelper studentClassHelperWrong = StudentClassHelper.builder()
              .classId(null)
              .build();
      String jsonStudentHelperTest = mapper.writeValueAsString(studentClassHelperWrong);
      mvc.perform(MockMvcRequestBuilders.put("/class/modify")
              .header("Authorization", userToken)
              .content(jsonStudentHelperTest)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isBadRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void DeleteClassTest(){
    try{
      mvc.perform(MockMvcRequestBuilders.delete("/class/1")
              .header("Authorization", userToken)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  @Test
  public void GetAllClassesTest(){
    try{
      mvc.perform(MockMvcRequestBuilders.get("/class/")
              .header("Authorization", userToken)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}