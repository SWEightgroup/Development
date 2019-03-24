package it.colletta.controller;

import it.colletta.model.Correction;
import it.colletta.service.CorrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/correction")
public class CorrectionController {

    @Autowired
    private CorrectionService correctionService;

    @RequestMapping(value = "/automatic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Correction> getCorrection(@RequestParam("text") String text) {
        try {
            return new ResponseEntity<Correction>(correctionService.getAutomaticCorrection("ciao come stai?"), HttpStatus.OK);
        }
        catch(IOException e) {
            return null;
        }

    }

}
