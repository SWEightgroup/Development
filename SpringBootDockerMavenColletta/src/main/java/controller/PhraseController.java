package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PhraseService;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/grammatical-analysis")
public class PhraseController {

  @Autowired PhraseService phraseService;

  /**
   * if you pass a string containing a phrase with an ending point, you will receive a grammatical
   * analysis of the phrase
   *
   * @param phrase
   * @return
   */
  @RequestMapping(value = "{phrase}", method = RequestMethod.GET, produces = "application/json")
  public String getSolution(@PathVariable("phrase") String phrase) {
    try {
      return phraseService.getSolution(phrase);
    } catch (IOException e) {
      e.printStackTrace();
      return new String();
    }
  }
}
