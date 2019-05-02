package it.colletta.controller;


import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.helper.FavoriteTeacherHelper;
import java.util.Arrays;
import java.util.Date;

import it.colletta.repository.user.StudentRepository;
import it.colletta.repository.user.UsersRepository;
import it.colletta.security.ParseJwt;
import it.colletta.service.student.StudentService;
import it.colletta.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

  private MockMvc mvc;
  private String userToken;
  private ObjectMapper mapper;
  private FavoriteTeacherHelper favoriteTeacherHelper;

  @Mock
  StudentService studentService;

  @Mock
  StudentRepository studentRepository;

  @InjectMocks
  StudentController studentController;

  @Before
  public void setup() {
    userToken = ("Bearer") + JWT.create().withJWTId("test@test.it").withSubject("test")
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));

    mapper = new ObjectMapper();
    mvc = MockMvcBuilders.standaloneSetup(studentController)
            .build();

    favoriteTeacherHelper = FavoriteTeacherHelper.builder()
            .teacherId(Arrays.asList("1", "2"))
            .build();
  }

  @Test
  public void ModifyFavoriteTeachersWithoutTokenTest() {
    try {
      String jsonFavoriteTeacherHelper = mapper.writeValueAsString(favoriteTeacherHelper);
      mvc.perform(MockMvcRequestBuilders.put("/students/favorite-teacher")
              .content(jsonFavoriteTeacherHelper)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isBadRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

 @Test
  public void ModifyFavoriteTeachersTest() {
    try {
      String jsonFavoriteTeacherHelper = mapper.writeValueAsString(favoriteTeacherHelper);
      mvc.perform(MockMvcRequestBuilders.put("/students/favorite-teacher")
              .header("Authorization", userToken)
              .content(jsonFavoriteTeacherHelper)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Test
  public void GetFavoriteTeachersTest() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/students/favorite-teacher")
              .header("Authorization", userToken)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void GetProgressTest() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/students/progress")
              .header("Authorization", userToken)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void addTeacherTest() {
    try {
      mvc.perform(MockMvcRequestBuilders.post("/students/favorite-teacher/123")
              .header("Authorization", userToken)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void deleteTeacherTest() {
    try {
      mvc.perform(MockMvcRequestBuilders.delete("/students/favorite-teacher/123")
              .header("Authorization", userToken)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
