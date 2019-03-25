package it.colletta.controller;

import it.colletta.model.PhraseModel;
import it.colletta.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phrase")
public class PhraseController {

    @Autowired
    private PhraseService phraseService;

    /**
     * @param phrase the text which needs to be inserted in the database
     * @return A new ResponseEntity that contains the phrase
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertPhrase(@RequestBody PhraseModel phrase) {
        return new ResponseEntity<String>
            (phraseService.insertPhrase(phrase).toString(), HttpStatus.OK);
    }

}
