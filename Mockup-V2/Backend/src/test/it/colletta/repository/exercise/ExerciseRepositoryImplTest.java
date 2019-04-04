package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.ExerciseModel;
import it.colletta.repository.config.MongoConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MongoConfig.class})
public class ExerciseRepositoryImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private ExerciseRepositoryImpl exerciseRepository;

    @Before
    public void setUp() throws Exception {

        exerciseRepository = new ExerciseRepositoryImpl(mongoTemplate);

        populateDatabase();
    }

    private void populateDatabase() {

        ArrayList<ExerciseModel> publicExercises = new ArrayList<>();
        publicExercises.add(ExerciseModel.builder()
                    .phraseText("first phrase")
                    .authorName("Bob Smith")
                    .authorId("1")
                    .build());

        publicExercises.add(ExerciseModel.builder()
                    .phraseText("first phrase")
                    .authorName("Tom Brown")
                    .authorId("2")
                    .build());

        publicExercises.add(ExerciseModel.builder()
                    .phraseText("second phrase")
                    .authorName("Tom Brown")
                    .authorId("2")
                    .build());

        mongoTemplate.insertAll(publicExercises);
    }

    @After
    public void tearDown() throws Exception {

        mongoTemplate.dropCollection("exercises");
    }

    @Test
    public void modifyAuthorName() {

        UpdateResult result = exerciseRepository.modifyAuthorName("Tom White","2");

        assertEquals(result.getModifiedCount(),2);

    }
}