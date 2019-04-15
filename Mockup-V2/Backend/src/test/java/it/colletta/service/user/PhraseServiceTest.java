package it.colletta.service.user;

import com.mongodb.client.FindIterable;
import it.colletta.model.PhraseModel;
import it.colletta.repository.phrase.PhraseRepository;
import it.colletta.service.PhraseService;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        properties =
                "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class PhraseServiceTest {

    @MockBean
    private PhraseRepository phraseRepository;

    private PhraseService phraseService;

    @Before
    public void setUp() throws Exception {
        phraseService = new PhraseService(phraseRepository);
    }

    @Test
    public void sentenceTest() {
        final FindIterable<Document> allPhrasesAsIterable = phraseRepository.findAllPhrasesAsIterable();

        for (Document phrase :
             allPhrasesAsIterable) {
            System.out.println(phrase);
        }
    }
}
