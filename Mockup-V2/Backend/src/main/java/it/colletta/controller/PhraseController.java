package it.colletta.controller;


import it.colletta.model.Correction;
import it.colletta.model.Phrase;
import it.colletta.service.CorrectionService;
import it.colletta.repository.CorrectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@RestController
@RequestMapping("/phrase")
public class PhraseController {

    @Autowired
    private PhraseService phraseService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insertPhrase(@RequestBody Phrase phrase) {

    }

}
