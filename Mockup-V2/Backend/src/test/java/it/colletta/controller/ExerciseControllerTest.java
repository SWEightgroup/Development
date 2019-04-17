package it.colletta.controller;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.ExerciseModel;
import it.colletta.model.helper.CorrectionHelper;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.service.ExerciseService;
import it.colletta.service.SolutionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
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

  @InjectMocks
  ExerciseController exerciseController;

  ExerciseHelper fakeExerciseHelper;

  CorrectionHelper fakeCorrectionHelper;

  ExerciseModel fakeExerciseModel;

  @Before
  public void setup() {
    userToken = ("Bearer") + JWT.create()
            .withJWTId("test@test.it")
            .withSubject("test")
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));

    mapper = new ObjectMapper();
    mvc = MockMvcBuilders.standaloneSetup(exerciseController)
            .alwaysExpect(status().isOk())
            .build();

    fakeExerciseHelper = ExerciseHelper.builder()
            .assignedUsersIds(Arrays.asList("1","2"))
            .phraseText("test")
            .mainSolution("solutionTest")
            .alternativeSolution("alternativeSolutionTest")
            .visibility(true)
            .author("authorTest")
            .language("it")
            .build();

    fakeExerciseModel = ExerciseModel.builder()
            .dateExercise(new Long(1234))
            .phraseId("1")
            .phraseText("test")
            .mainSolutionId("solutionTest")
            .authorName("authorTest")
            .authorId("authorIdTest")
            .visibility(true)
            .build();

    fakeCorrectionHelper = new CorrectionHelper("", "test");
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

  @Test
  public void InsertExerciseTest(){
    try{

      String jsonFakeExerciseHelper = mapper.writeValueAsString(fakeExerciseHelper);
      mvc.perform(MockMvcRequestBuilders
              .post("/exercises/insert-exercise")
              .header("Authorization", userToken)
              .content(jsonFakeExerciseHelper)
              .contentType(MediaType.APPLICATION_JSON_VALUE));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void insertFreeExerciseTest(){
    try{
      String jsonFakeExerciseHelper = mapper.writeValueAsString(fakeExerciseHelper);
      mvc.perform(MockMvcRequestBuilders
              .post("/exercises/student/insert-free-exercise")
              .header("Authorization", userToken)
              .content(jsonFakeExerciseHelper)
              .contentType(MediaType.APPLICATION_JSON_VALUE));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  /*@Test
  public void doExerciseTest(){
    try{
      String jsonFakeCorrectionHelper = mapper.writeValueAsString(fakeCorrectionHelper);
      mvc.perform(MockMvcRequestBuilders
              .post("/exercises//student/do")
              .header("Authorization", userToken)
              .content(jsonFakeCorrectionHelper)
              .contentType(MediaType.APPLICATION_JSON_VALUE));
    } catch(Exception e){
      e.printStackTrace();
    }
  } */

}
