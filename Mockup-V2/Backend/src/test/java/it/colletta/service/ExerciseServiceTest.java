package it.colletta.service;

import it.colletta.model.ExerciseModel;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.repository.exercise.ExerciseRepository;
import it.colletta.repository.phrase.PhraseRepository;
import it.colletta.repository.user.UsersRepository;
import it.colletta.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

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

    @Mock
    private PhraseRepository phraseRepository;


    private PhraseService phraseService;

    private UserService userService;

    private ExerciseService exerciseService;

    private ExerciseModel testExercise;

    private ExerciseHelper exercise;

    private PhraseModel phrase;

    private SolutionModel mainSolution;


    @Before
    public void setUp() throws Exception {

       exerciseService = new ExerciseService(exerciseRepository,phraseService,userService);
       userService = new UserService(usersRepository);
       phraseService = new PhraseService(phraseRepository);


            testExercise = ExerciseModel.builder()
                .id("1")
                .phraseId("12")
                .phraseText("questa è una prova")
                .dateExercise(1554574902653L)
                .mainSolutionId("22")
                .alternativeSolutionId("22")
                .authorName("Insegnante Insegnante")
                .authorId("100")
                .visibility(true)
                .build();

        List<String> assignedUsersIds = new ArrayList<>();;
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


    }

    @Test
    public void insertExercise() {
        /*
        Mockito.when(phraseService.insertPhrase(phrase)).thenReturn(phrase);
        Mockito.when(exerciseRepository.save(testExercise)).thenReturn(testExercise);
        phraseService.increaseReliability(mainSolution);
        userService.addExerciseItem(exercise.getAssignedUsersIds(), testExercise);

        ExerciseModel addedExercise = exerciseService.insertExercise(exercise);

        assertEquals(addedExercise.getId(), "1");
        assertEquals(addedExercise.getAlternativeSolutionId(), "22");
        assertEquals(addedExercise.getAuthorId(), "100");
        assertEquals(addedExercise.getAuthorName(), "Insegnante Insegnante");
        assertEquals(addedExercise.getMainSolutionId(), "22");
        assertEquals(addedExercise.getPhraseText(), "questa è una prova");
        assertEquals(addedExercise.getDateExercise(), "1554574902653L");
        assertEquals(addedExercise.getPhraseId(), "12");
        assertEquals(addedExercise.getVisibility(), true);

         */

    }

    @Test
    public void findById(){
        Mockito.when(exerciseRepository.findById("1")).thenReturn(Optional.of(testExercise));

        Optional<ExerciseModel> findExercise = exerciseRepository.findById("1");

        Assert.assertNotNull(findExercise);
        assertEquals(findExercise.get().getId(), "1");
    }


}