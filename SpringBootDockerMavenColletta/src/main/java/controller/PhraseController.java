package controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import resources.PhraseModel;
import service.PhraseService;

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
  @RequestMapping(value = "/p", method = RequestMethod.POST, produces = "application/json" ,consumes = "application/json" )
  public String getSolution(@RequestBody PhraseModel phrase) {
    try {    	
      return phraseService.getSolution(phrase.phrase);
    } catch (IOException e) {
      e.printStackTrace();
      return new String();
    }
  }
  

}
