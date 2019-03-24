package it.colletta.controller;

import it.colletta.model.CorrectionModel;
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

    /**
     * @param text the text which has to be analyzed by freeling
     * @return A CorrectionModel with the analyzed sentence or empty if the service is unavailable
     */
    @RequestMapping(value = "/automatic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CorrectionModel> getCorrection(@RequestParam("text") String text) {
        try {
            return new ResponseEntity<CorrectionModel>
                (correctionService.getAutomaticCorrection(text), HttpStatus.OK);
        }
        catch(IOException e) {
            return new ResponseEntity<CorrectionModel>
                (new CorrectionModel(), HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

}
