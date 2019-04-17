package it.colletta.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.ArgumentMatchers.any;

import it.colletta.model.ExerciseModel;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.repository.exercise.ExerciseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.colletta.repository.user.UsersRepository;
import it.colletta.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
    properties =
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class ExerciseServiceTest {

  @Autowired
  private ExerciseService exerciseService;

  @Autowired
  private PhraseService phraseService;

  @Autowired
  private UserService userService;

  @Autowired
  private SolutionService solutionService;


  @MockBean
  private ExerciseRepository exerciseRepository;

  @MockBean
  private UsersRepository usersRepository;


  private ExerciseModel exerciseModel;

  private ExerciseHelper exercise;

  private UserModel userModel;

  private PhraseModel phrase;

  private SolutionModel mainSolution;


  @Before
  public void setUp() throws Exception {
    exerciseService = new ExerciseService(exerciseRepository,phraseService,userService,solutionService);

    exerciseModel = ExerciseModel.builder()
        .id("1")
        .phraseId("12")
        .phraseText("questa è una prova")
        .dateExercise(any(Long.class))
        .mainSolutionId("22")
        .alternativeSolutionId("22")
        .authorName("Insegnante Insegnante")
        .authorId("100")
        .visibility(true)
        .build();

    List<String> assignedUsersIds = new ArrayList<>();
    assignedUsersIds.add("104");
    exercise = ExerciseHelper.builder()
        .id("15")
        .assignedUsersIds(assignedUsersIds)
        .phraseText("questa è una prova")
        .mainSolution("22")
        .alternativeSolution("22")
        .visibility(true)
        .author("100")
        .date(1554574902653L)
        .language("it")
        .build();

    phrase = PhraseModel.builder()
        .id("12")
        .language(exercise.getLanguage())
        .datePhrase(exercise.getDate())
        .phraseText(exercise.getPhraseText())
        .build();

    mainSolution = SolutionModel.builder()
        .id("22")
        .reliability(0)
        .authorId(exercise.getAuthor())
        .solutionText(exercise.getMainSolution())
        .build();

    userModel =
            UserModel.builder()
                    .id("100")
                    .firstName("Insegnante")
                    .lastName("Insegnante")
                    .build();


  }

  @Test
  public void insertExercise() {
    Mockito.when(exerciseRepository.save(exerciseModel)).thenReturn(exerciseModel);
    Mockito.when(usersRepository.findById("100")).thenReturn(Optional.of(userModel));
    assertThat(exerciseService.insertExercise(exercise)).isEqualTo(exerciseModel);
  }
/*
  @Test
  public void findById() {
      Optional<ExerciseModel> model = null;
      Mockito.when(exerciseService.findById("5ca9327583406d30249f9121")).thenReturn(model);
      if(model.isPresent()) {
        assertEquals(model.get().getId(), "5ca9327583406d30249f9121");
      }
      else {
        assert false;
      }
  }

 */
}