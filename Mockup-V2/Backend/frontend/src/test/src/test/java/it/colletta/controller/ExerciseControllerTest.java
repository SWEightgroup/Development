package it.colletta.controller;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static it.colletta.security.SecurityConstants.EXPIRATION_TIME;
import static it.colletta.security.SecurityConstants.SECRET;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.colletta.model.ExerciseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.helper.CorrectionHelper;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.service.ExerciseService;
import it.colletta.service.SolutionService;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseControllerTest {

  private MockMvc mvc;
  private String userToken;
  private ObjectMapper mapper;
  private ExerciseHelper fakeExerciseHelper;
  private CorrectionHelper fakeCorrectionHelper;
  private ExerciseModel fakeExerciseModel;
  private SolutionModel fakeSolutionModel;

  @Mock
  ExerciseService exerciseService;

  @Mock
  SolutionService solutionService;

  @InjectMocks
  ExerciseController exerciseController;

  @Before
  public void setup() {
    userToken = ("Bearer") + JWT.create().withJWTId("test@test.it").withSubject("test")
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(HMAC512(SECRET.getBytes()));

    mapper = new ObjectMapper();
    mvc = MockMvcBuilders.standaloneSetup(exerciseController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .setViewResolvers(new ViewResolver() {
              @Override
              public View resolveViewName(String viewName, Locale locale) throws Exception {
                return new MappingJackson2JsonView();
              }
            })
        .build();

    fakeExerciseHelper = ExerciseHelper.builder().assignedUsersIds(Arrays.asList("1", "2"))
        .phraseText("test")
        .mainSolution("solutionTest").alternativeSolution("alternativeSolutionTest")
        .visibility(true)
        .author("authorTest").language("it").build();

    fakeExerciseModel = ExerciseModel.builder().dateExercise(new Long(1234)).phraseId("1")
        .phraseText("test")
        .mainSolutionId("solutionTest").authorName("authorTest").authorId("authorIdTest")
        .visibility(true).build();

    fakeCorrectionHelper = new CorrectionHelper("prova", "11");

    fakeSolutionModel = SolutionModel.builder().solutionText("test").reliability(2)
        .authorId("provaInsegnante")
        .mark(0.0).build();
  }

  @Test
  public void checkAuthorityExerciseControllerTest() {
    try {
      mvc.perform(MockMvcRequestBuilders.get("/exercises/done")).andExpect(status().isBadRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void ExerciseUserDone() {
  }

  @Test
  public void ExerciseToDo() {
  }

  @Test
  public void getFavouritePublicTest(){
    /*try {
      mvc.perform(MockMvcRequestBuilders.get("/exercises/favourite-public")
              .header("Authorization", userToken)
              .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    } */
  }

  @Test
  public void InsertExerciseTest() {
    try {
      String jsonFakeExerciseHelper = mapper.writeValueAsString(fakeExerciseHelper);
      mvc.perform(MockMvcRequestBuilders.post("/exercises/insert-exercise")
          .header("Authorization", userToken)
          .content(jsonFakeExerciseHelper).contentType(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void insertFreeExerciseTest() {
    try {
      String jsonFakeExerciseHelper = mapper.writeValueAsString(fakeExerciseHelper);
      mvc.perform(
          MockMvcRequestBuilders.post("/exercises/student/insert-free-exercise")
              .header("Authorization", userToken)
              .content(jsonFakeExerciseHelper).contentType(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void doExerciseTest() {
    try {
      String jsonFakeCorrectionHelper = mapper.writeValueAsString(fakeCorrectionHelper);
      mvc.perform(
          MockMvcRequestBuilders.post("/exercises/student/do").header("Authorization", userToken)
              .content(jsonFakeCorrectionHelper).contentType(MediaType.APPLICATION_JSON_VALUE))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void automaticSolutionTest() {
    try {
      Map<String, String> articleMapOne = new HashMap<>();
      articleMapOne.put("ar01", "test");
      String jsonMapCorrection = mapper.writeValueAsString(articleMapOne);
      mvc.perform(
              MockMvcRequestBuilders.post("/exercises/student/do")
                      .header("Authorization", userToken)
                      .content(jsonMapCorrection)
                      .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void deleteExerciseTest() {
    try {
      mvc.perform(
              MockMvcRequestBuilders.delete("/exercises/1")
                      .header("Authorization", userToken)
                      .contentType(MediaType.APPLICATION_JSON_VALUE))
              .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
