package it.colletta.service;

import it.colletta.model.PhraseModel;
import it.colletta.repository.phrase.PhraseRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class PhraseServiceTest {

  @Mock
  private PhraseRepository phraseRepository;

  @InjectMocks
  private PhraseService phraseService;

  private PhraseModel phrase;

  @Before
  public void setUp(){

    phrase = PhraseModel.builder()
            .id("321")
            .language("it")
            .datePhrase(378136781L)
            .phraseText("questa è una prova")
            .build();
  }


  @Test
  public void insertPhrase(){

    Mockito.when(phraseRepository.getPhraseWithText(anyString())).thenReturn(Optional.of(phrase));

    Mockito.when(phraseRepository.save(any(PhraseModel.class))).thenAnswer(returnsFirstArg());

    PhraseModel phraseReturn = phraseService.insertPhrase(phrase);

    assertEquals(phraseReturn.getPhraseText(), "questa è una prova");
    assertEquals(phraseReturn.getLanguage(), "it");

  }


}
