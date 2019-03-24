package it.colletta.controller;

import it.colletta.model.CorrectionModel;
import it.colletta.model.PhraseModel;
import it.colletta.service.CorrectionService;
import it.colletta.service.PhraseService;
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

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insertPhrase(@RequestBody PhraseModel phrase) {
        phraseService.insertPhrase(phrase);
    }

}
