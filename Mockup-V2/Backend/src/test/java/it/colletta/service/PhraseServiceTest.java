package it.colletta.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.repository.phrase.PhraseRepository;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PhraseServiceTest {

  @Mock
  private PhraseRepository phraseRepository;

  @InjectMocks
  private PhraseService phraseService;

  private PhraseModel phrase;

  private SolutionModel mainSolution;

  @Before
  public void setUp() {

    phrase = PhraseModel.builder()
        .id("321")
        .language("it")
        .datePhrase(378136781L)
        .phraseText("questa è una prova")
        .build();

    mainSolution = SolutionModel.builder()
        .id("1246")
        .reliability(0)
        .authorId("100")
        .solutionText("[\"AP0MN3S\",\"NPNNG0D\",\"RG\",\"DE2FSS\"]")
        .build();
  }


  @Test
  public void insertPhrase() {

    Mockito.when(phraseRepository.findPhraseModelByPhraseTextIs(anyString()))
        .thenReturn(Optional.of(phrase));

    Mockito.when(phraseRepository.save(any(PhraseModel.class))).thenAnswer(returnsFirstArg());

    PhraseModel phraseReturn = phraseService.insertPhrase(phrase);

    assertEquals(phraseReturn.getPhraseText(), "questa è una prova");
    assertEquals(phraseReturn.getLanguage(), "it");

  }

  @Test
  public void getPhraseById() {
    Mockito.when(phraseRepository.findById(anyString())).thenReturn(Optional.of(phrase));
    Optional<PhraseModel> phraseReturn = phraseService.getPhraseById(phrase.getId());
    if (phraseReturn.isPresent()) {
      assertEquals(phraseReturn.get().getPhraseText(), "questa è una prova");
      assertEquals(phraseReturn.get().getLanguage(), "it");
    }
  }

  @Test
  public void increaseReliability() {

    phraseService.increaseReliability(mainSolution);
    Mockito.verify(phraseRepository).increaseReliability(mainSolution);

  }


  @Test
  public void savePhrase() {
    phraseService.savePhrase(phrase);
    Mockito.verify(phraseRepository).save(phrase);
  }


  @Test
  public void getSolutionInPhrase() {
      /*Mockito.when(phraseRepository.getSolution(anyString(), anyString(), anyString())).thenReturn(mainSolution);
      SolutionModel mysolution = phraseService.getSolutionInPhrase(phrase.getId(),mainSolution.getId());

      assertEquals(mysolution.getSolutionText(), "[\"AP0MN3S\",\"NPNNG0D\",\"RG\",\"DE2FSS\"]");*/
    //fixme
  }

  @Test
  public void createPhrase() {

    PhraseModel myPhrase = phraseService.createPhrase(phrase.getPhraseText(), phrase.getLanguage());

    assertEquals(myPhrase.getPhraseText(), phrase.getPhraseText());
    assertEquals(myPhrase.getLanguage(), phrase.getLanguage());

  }


}




