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
  @RequestMapping(value = "/p", method = RequestMethod.POST, produces = "application/json")
  public String getSolution(@RequestBody Data phrase) {
    try {
      return phraseService.getSolution(phrase.getPhrase());
    } catch (IOException e) {
      e.printStackTrace();
      return new String();
    }
  }
  

}
