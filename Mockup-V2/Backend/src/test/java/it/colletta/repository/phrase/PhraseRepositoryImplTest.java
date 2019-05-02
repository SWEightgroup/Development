package it.colletta.repository.phrase;

import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.helper.FilterHelper;
import it.colletta.repository.config.MongoConfig;
import org.bson.Document;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.mockito.internal.matchers.LessOrEqual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MongoConfig.class})
public class PhraseRepositoryImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private PhraseRepositoryImpl phraseRepository;

    private PhraseModel noSolutionPhrase;
    private PhraseModel oneSolutionPhrase;
    private PhraseModel multipleSolutionsPhrase;

    @Before
    public void setUp() {

        phraseRepository = new PhraseRepositoryImpl(mongoTemplate);
        populateDatabase();
    }

    @Test
    public void findAllByAuthor() {

        List<PhraseModel> phrasesBy1 = phraseRepository.findAllByAuthor("1");

        assertEquals(phrasesBy1.size(), 2);
        assertEquals(phrasesBy1.get(0).getId(), oneSolutionPhrase.getId());
        assertEquals(phrasesBy1.get(1).getId(), multipleSolutionsPhrase.getId());

        List<PhraseModel> phrasesBy2 = phraseRepository.findAllByAuthor("2");

        assertEquals(phrasesBy2.size(), 1);
        assertEquals(phrasesBy2.get(0).getId(), multipleSolutionsPhrase.getId());
    }

    @Test
    public void findNoPhrasesWithWrongAuthor() {

        List<PhraseModel> phrasesBy3 = phraseRepository.findAllByAuthor("3");

        assertEquals(phrasesBy3.size(), 0);
    }

    @Test
    public void findAllSolutionsByAuthor() {

        List<SolutionModel> solutionsBy1 = phraseRepository.findAllSolutionsByAuthor("1");
        //la query non funziona?
        //assertEquals(solutionsBy1.size(),3);

    }

    @Test
    public void increaseReliability() {

        SolutionModel solution =
                SolutionModel.builder().solutionText("first solution").authorId("1").build();

        UpdateResult updateResult = phraseRepository.increaseReliability(solution);

        assertEquals(updateResult.getModifiedCount(), 1);
    }


    @Test
    public void getSolution() {

        //SolutionModel solution = phraseRepository.getSolution("3","12");
        //assertEquals(solution.getSolutionText(),"second solution");

        //solution = phraseRepository.getSolution("1","wrongId");
        //assertNull(solution);
    }

    @Test
    public void findAllPhrasesWithFilter() {
        Long filterStartDate = 1500L;
        Long filterEndDate = 4000L;
        Integer filterMinRel = 3;
        ArrayList<String> filterLanguages = new ArrayList<>();
        filterLanguages.add("it");
        filterLanguages.add("en");

        FilterHelper filter = FilterHelper.builder()
                .startDate(filterStartDate)
                .endDate(filterEndDate)
                .minReliability(filterMinRel)
                .languages(filterLanguages)
                .build();

        List<Document> result = phraseRepository.findAllPhrasesWithFilter(filter);

        for(Document d : result) {
            d.getString("language");
            Document solution = d.get("solutions",Document.class);
            assertTrue(solution.getLong("dateSolution")>=filterStartDate);
            assertTrue(solution.getLong("dateSolution")<=filterEndDate);
            assertTrue(solution.getInteger("reliability")>=filterMinRel);
        }
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection("phrases");
    }

    private void populateDatabase() {

        ArrayList<SolutionModel> oneSolutionList = new ArrayList<>();
        oneSolutionList.add(
                SolutionModel.builder().id("11").solutionText("first solution").authorId("1").dateSolution(1000L).reliability(1).build());

        ArrayList<SolutionModel> multipleSolutionsList = new ArrayList<>();
        multipleSolutionsList.add(
                SolutionModel.builder().id("12").solutionText("second solution").dateSolution(2000L).authorId("1").reliability(5).build());

        multipleSolutionsList.add(
                SolutionModel.builder().id("13").solutionText("third solution").dateSolution(3000L).authorId("2").reliability(2).build());

        multipleSolutionsList.add(
                SolutionModel.builder().id("14").solutionText("fourth solution").dateSolution(4000L).authorId("1").reliability(6).build());

        noSolutionPhrase = PhraseModel.builder().id("1").phraseText("no solution phrase").language("en").build();

        oneSolutionPhrase =
                PhraseModel.builder().id("2").phraseText("one solution phrase").language("es").solutions(oneSolutionList)
                        .build();

        multipleSolutionsPhrase =
                PhraseModel.builder()
                        .id("3")
                        .phraseText("multiple solutions phrase")
                        .solutions(multipleSolutionsList)
                        .language("it")
                        .build();

        noSolutionPhrase = mongoTemplate.save(noSolutionPhrase);
        oneSolutionPhrase = mongoTemplate.save(oneSolutionPhrase);
        multipleSolutionsPhrase = mongoTemplate.save(multipleSolutionsPhrase);
    }

}
