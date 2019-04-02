package it.colletta.repository.phrase;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.repository.config.MongoConfig;
import it.colletta.repository.user.UsersRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;



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
    public void setUp() throws Exception {

        phraseRepository = new PhraseRepositoryImpl(mongoTemplate);

        ArrayList<SolutionModel> oneSolutionList = new ArrayList<>();
        oneSolutionList.add(SolutionModel.builder()
                .solutionText("first solution")
                .authorId("1")
                .build());

        ArrayList<SolutionModel> multipleSolutionsList = new ArrayList<>();
        multipleSolutionsList.add(SolutionModel.builder()
                .solutionText("second solution")
                .authorId("1")
                .build());

        multipleSolutionsList.add(SolutionModel.builder()
                .solutionText("third solution")
                .authorId("2")
                .build());

        multipleSolutionsList.add(SolutionModel.builder()
                .solutionText("fourth solution")
                .authorId("1")
                .build());

        noSolutionPhrase = PhraseModel.builder()
                .phraseText("no solution phrase")
                .build();

        oneSolutionPhrase = PhraseModel.builder()
                .phraseText("one solution phrase")
                .solutions(oneSolutionList)
                .build();

        multipleSolutionsPhrase = PhraseModel.builder()
                .phraseText("multiple solutions phrase")
                .solutions(multipleSolutionsList)
                .build();

        noSolutionPhrase = mongoTemplate.save(noSolutionPhrase);
        oneSolutionPhrase = mongoTemplate.save(oneSolutionPhrase);
        multipleSolutionsPhrase = mongoTemplate.save(multipleSolutionsPhrase);

    }

    @Test
    public void findAllByAuthor() {

        List<PhraseModel> phrasesBy1 = phraseRepository.findAllByAuthor("1");

        assertEquals(phrasesBy1.size(), 2);
        assertEquals(phrasesBy1.get(0).getId(),oneSolutionPhrase.getId());
        assertEquals(phrasesBy1.get(1).getId(),multipleSolutionsPhrase.getId());


        List<PhraseModel> phrasesBy2 = phraseRepository.findAllByAuthor("2");

        assertEquals(phrasesBy2.size(), 1);
        assertEquals(phrasesBy2.get(0).getId(),multipleSolutionsPhrase.getId());

        
        List<PhraseModel> phrasesBy3 = phraseRepository.findAllByAuthor("3");

        assertEquals(phrasesBy3.size(),0);

    }

    @Test
    public void findAllSolutionsByAuthor() {

        List<SolutionModel> solutionsBy1 = phraseRepository.findAllSolutionsByAuthor("1");

        //assertEquals(solutionsBy1.size(),3);

    }

    @Test
    public void increaseReliability() {
        
    }
}