package it.colletta.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.colletta.resources.SentenceModel;
import it.colletta.service.SentenceService;

@CrossOrigin
@RestController
@RequestMapping("/grammatical-analysis")
public class SentenceController {

  @Autowired SentenceService sentenceService;

  /** 
   * If you pass a string containing a sentence with 
   * an ending point, you will receive a grammatical
   * analysis of the sentence.
   *
   * @param sentence sentence to analyze
   * @return freeLing solution
   */



  
  @RequestMapping  (value = "/p", method = RequestMethod.POST , produces = "application/json" ,consumes = "application/json" )
  public String getSolution(@RequestBody SentenceModel sentence) {
    try {
      return sentenceService.getSolution(sentence.sentence).trim();
    } catch (IOException error) {
      error.printStackTrace();
      return new String();
    }
  }

}
