package it.colletta.service;

import it.colletta.model.ExerciseModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.repository.exercise.ExerciseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        properties =
                "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class ExerciseServiceTest {

    @MockBean
    private ExerciseRepository exerciseRepository;

    private ExerciseService exerciseService;

    private ExerciseModel testExercise;

    private ExerciseHelper exercise;

    @Before
    public void setUp() throws Exception {

       /* exerciseService = new ExerciseService(exerciseRepository);

        testExercise = ExerciseModel.builder()
                .id("5ca8ee3683406d2494b2f526")
                .phraseId("5ca8ee3683406d2494b2f525")
                .phraseText("questa è una prova")
                .dateExercise(1554574902653L)
                .mainSolutionId("5ca8ee3683406d2494b2f524")
                .authorName("Insegnante Insegnante")
                .authorId("5ca8aff783406d605088b090")
                .visibility(true)
                .build();

        List<String> assignedUsersIds = new ArrayList<>();;
        assignedUsersIds.add("104");


         exercise = ExerciseHelper.builder()
                 .id("1")
                 .assignedUsersIds(assignedUsersIds)
                 .phraseText("questa è una prova")
                 .mainSolution("5ca8ee3683406d2494b2f524")
                 .visibility(true)
                 .author("5ca8aff783406d605088b090")
                 .date(1554574902653L)
                 .language("it")
                 .build();
                 */

    }

    @Test
    public void insertExercise() {

        /*
        Mockito.when(exerciseRepository.save(testExercise)).thenReturn(testExercise);


        ExerciseModel addedExercise = exerciseService.insertExercise(exercise);

        assertEquals(addedExercise.getId(), "1");
        assertEquals(addedExercise.getAlternativeSolutionId(), "2");
        assertEquals(addedExercise.getAuthorId(), "11");
        assertEquals(addedExercise.getAuthorName(), "pippo");
        assertEquals(addedExercise.getMainSolutionId(), "0");
        assertEquals(addedExercise.getAlternativeSolutionId(), "2");
        assertEquals(addedExercise.getPhraseText(), "Sono una frase");
        assertEquals(addedExercise.getDateExercise(), "1220227200L");
        assertEquals(addedExercise.getPhraseId(), "10");
        assertEquals(addedExercise.getVisibility(), true);

         */
    }


}