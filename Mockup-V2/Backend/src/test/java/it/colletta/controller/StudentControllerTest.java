package it.colletta.controller;


import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.ExerciseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.helper.CorrectionHelper;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.model.helper.FavoriteTeacherHelper;
import it.colletta.service.ExerciseService;
import it.colletta.service.SolutionService;
import java.util.Arrays;
import java.util.Date;

import it.colletta.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import javax.servlet.Filter;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

  private MockMvc mvc;
  private String userToken;
  private ObjectMapper mapper;
  private FavoriteTeacherHelper favoriteTeacherHelper;

  @Mock
  private UserService userService;

  @InjectMocks
  private StudentController studentController;

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
  @WithMockUser(roles={"STUDENT"})
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
  @WithMockUser(roles={"STUDENT"})
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

}
