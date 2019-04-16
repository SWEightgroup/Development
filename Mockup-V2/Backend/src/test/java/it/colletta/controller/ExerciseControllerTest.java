package it.colletta.controller;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.service.ExerciseService;
import it.colletta.service.SolutionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.EntityLinks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseControllerTest {

  private MockMvc mvc;
  private String userToken;
  private ObjectMapper mapper;

  @Mock
  private ExerciseService exerciseService;

  @Mock
  private SolutionService solutionService;

  @Mock
  private EntityLinks links;


  @Before
  public void setup() {
    userToken = ("Bearer") + JWT.create()
            .withJWTId("test@test.it")
            .withSubject("test")
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));

    mapper = new ObjectMapper();
    final ExerciseController exerciseController = new ExerciseController();
    exerciseController.setEntityLink(links);
    exerciseController.setExerciseService(exerciseService);
    exerciseController.setSolutionService(solutionService);

    mvc = MockMvcBuilders.standaloneSetup(exerciseController)
            .alwaysExpect(status().isOk())
            .build();
  }

  @Test
  public void ExerciseUserDone() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/exercises/done").header("Authorization", userToken));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void ExerciseToDo() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/exercises/user-todo").header("Authorization", userToken));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /*
  @Test
  public void InsertExerciseTest(){
    try{
      mvc.perform(MockMvcRequestBuilders
              .post("/exercises/insert-exercise")
              .header("Authorization", userToken)
              .param("","")
              .param("","")
              .param("",""));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  */
}
