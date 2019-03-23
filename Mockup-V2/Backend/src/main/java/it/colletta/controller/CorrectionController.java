package it.colletta.controller;

import it.colletta.model.Correction;
import it.colletta.service.CorrectionService;
import it.colletta.repository.CorrectionRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@RestController
@RequestMapping("/correction")
public class CorrectionController {

    private CorrectionService correctionService;

    public CorrectionController(CorrectionRepository correctionRepository) {
        correctionService = new CorrectionService(correctionRepository);
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Correction getCorrection(@RequestParam("test") String Id) {

        Correction correction = correctionService.test(Id);
        return correction;
    }
}