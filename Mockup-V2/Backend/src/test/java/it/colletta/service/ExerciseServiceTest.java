package it.colletta.service;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.assertEquals;
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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource(
    properties =
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class ExerciseServiceTest {

  @Mock
  private ExerciseRepository exerciseRepository;

  @Mock
  private UsersRepository usersRepository;

  @InjectMocks
  private ExerciseService exerciseService;

  @Mock
  private PhraseService phraseService;

  @Mock
  private SolutionService solutionService;

  @Mock
  private UserService userService;

  private ExerciseModel exerciseModel;

  private ExerciseHelper exercise;

  private UserModel userModel;

  private PhraseModel phrase;

  private SolutionModel mainSolution;


  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);

    exerciseModel = ExerciseModel.builder()
        .id(any(String.class))
        .phraseId(any(String.class))
        .phraseText("questa è una prova")
        .dateExercise(any(Long.class))
        .mainSolutionId(any(String.class))
        .alternativeSolutionId(any(String.class))
        .authorName("Insegnante Insegnante")
        .authorId(any(String.class))
        .visibility(true)
        .build();

    List<String> assignedUsersIds = new ArrayList<>();
    assignedUsersIds.add(any(String.class));
    exercise = ExerciseHelper.builder()
        .id(any(String.class))
        .assignedUsersIds(assignedUsersIds)
        .phraseText("questa è una prova")
        .mainSolution("main solution")
        .alternativeSolution("alternative solution")
        .visibility(true)
        .author(any(String.class))
        .date(any(Long.class))
        .language("it")
        .build();

    phrase = PhraseModel.builder()
        .id(any(String.class))
        .language(exercise.getLanguage())
        .datePhrase(exercise.getDate())
        .phraseText(exercise.getPhraseText())
        .build();

    mainSolution = SolutionModel.builder()
        .id(any(String.class))
        .reliability(0)
        .authorId(exercise.getAuthor())
        .solutionText(exercise.getMainSolution())
        .build();

    userModel =
            UserModel.builder()
                    .id(any(String.class))
                    .firstName("Insegnante")
                    .lastName("Insegnante")
                    .build();


  }

  @Test
  public void insertExercise() {

    Mockito.when(phraseService.createPhrase(exercise.getPhraseText(),exercise.getLanguage())).thenReturn(phrase);
    Mockito.when(solutionService.createSolution(exercise.getMainSolution(),exercise.getAuthor())).thenReturn(mainSolution);
    Mockito.when(phraseService.insertPhrase(phrase)).thenReturn(phrase);
    Mockito.when(exerciseRepository.save(exerciseModel)).thenReturn(exerciseModel);
    Mockito.when(userService.findById(exercise.getAuthor())).thenReturn(Optional.of(userModel));

    ExerciseModel myAddedExercise = exerciseService.insertExercise(exercise);

    assertEquals(myAddedExercise.getAuthorName(),"Insegnante Insegnante");
    assertEquals(myAddedExercise.getPhraseText(),"questa è una prova");

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