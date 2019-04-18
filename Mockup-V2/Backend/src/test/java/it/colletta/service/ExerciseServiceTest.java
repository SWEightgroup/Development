package it.colletta.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import it.colletta.model.ExerciseModel;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.repository.exercise.ExerciseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.colletta.repository.phrase.PhraseRepository;
import it.colletta.repository.user.UsersRepository;
import it.colletta.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.AdditionalAnswers.returnsFirstArg;



@RunWith(MockitoJUnitRunner.class)
public class ExerciseServiceTest {

  @Mock
  private ExerciseRepository exerciseRepository;

  @Mock
  private UsersRepository usersRepository;

  @Mock
  private PhraseRepository phraseRepository;

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

    //MockitoAnnotations.initMocks(this);

    exerciseModel = ExerciseModel.builder()
        .id("1")
        .phraseId("10")
        .phraseText("questa è una prova")
        .dateExercise(378136781L)
        .mainSolutionId("22")
        .alternativeSolutionId("22")
        .authorName("Insegnante Insegnante")
        .authorId("100")
        .visibility(true)
        .build();

    List<String> assignedUsersIds = new ArrayList<>();
    assignedUsersIds.add("104");
    exercise = ExerciseHelper.builder()
        .id("123")
        .assignedUsersIds(assignedUsersIds)
        .phraseText("questa è una prova")
        .mainSolution("main solution")
        .alternativeSolution("alternative solution")
        .visibility(true)
        .author("100")
        .date(378136781L)
        .language("it")
        .build();

    phrase = PhraseModel.builder()
        .id("321")
        .language(exercise.getLanguage())
        .datePhrase(exercise.getDate())
        .phraseText(exercise.getPhraseText())
        .build();

    mainSolution = SolutionModel.builder()
        .id("1246")
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

  /**
   * Test insertExercise method.
   *
   */

  @Test
  public void insertExercise() {

    Mockito.when(phraseService.createPhrase(exercise.getPhraseText(),exercise.getLanguage())).thenReturn(phrase);
    Mockito.when(solutionService.createSolution(exercise.getMainSolution(),exercise.getAuthor())).thenReturn(mainSolution);
    Mockito.when(phraseService.insertPhrase(phrase)).thenReturn(phrase);
    Mockito.when(userService.findById(exercise.getAuthor())).thenReturn(Optional.of(userModel));

    ExerciseModel myAddedExercise = exerciseService.insertExercise(exercise);

    assertEquals(myAddedExercise.getAuthorName(),"Insegnante Insegnante");
    assertEquals(myAddedExercise.getPhraseText(),"questa è una prova");
  }

  /**
   * Test insertExercise method.
   *
   */

  @Test
  public void insertFreeExercise() {
    Mockito.when(phraseService.insertPhrase(any(PhraseModel.class))).thenAnswer(returnsFirstArg());
    Mockito.when(userService.findById(userModel.getId())).thenReturn(Optional.of(userModel));

    ExerciseModel myAddedExercise = exerciseService.insertFreeExercise(exercise,exercise.getAuthor());

    assertEquals(myAddedExercise.getAuthorName(),"Insegnante Insegnante");
    assertEquals(myAddedExercise.getPhraseText(),"questa è una prova");
  }


  /**
   * Test findById method.
   *
   */

  @Test
  public void findById() {
    Mockito.when(exerciseRepository.findById(anyString())).thenReturn(Optional.of(exerciseModel));
    Optional<ExerciseModel> myidfound = exerciseService.findById(exerciseModel.getId());

    Assert.assertNotNull(myidfound);
    Mockito.verify(exerciseRepository, Mockito.times(1)).findById(anyString());
    Mockito.verifyNoMoreInteractions(exerciseRepository);

  }





}