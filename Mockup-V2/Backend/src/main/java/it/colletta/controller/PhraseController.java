package it.colletta.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.colletta.model.SolutionModel;
import it.colletta.model.PhraseModel;
import it.colletta.service.PhraseService;


@RestController
@RequestMapping("/phrase")
public class PhraseController {

    @Autowired
    private PhraseService phraseService;

    /**
     * Searching all the phrases inserted by a user, teacher or student, without solutions
     * 
     * @param userId the unique Id of the student inside the database
     * @return new ResponseEntity that contains all the phrases of a target user without solutions
     * 
     * 
     */
    @RequestMapping(value = "/get-all-phrases/{userId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhraseModel>> getAllPhraseSolution(
            @PathVariable("userId") String userId) {
        try {
            return new ResponseEntity<List<PhraseModel>>(phraseService.getAllPhrases(userId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<PhraseModel>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get-all-solutions/{userId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SolutionModel>> findAllSolutionsByAuthor(
            @PathVariable("userId") String authorId) {

            try {
                return new ResponseEntity<>(phraseService.findAllSolutionsByAuthor(authorId),
                        HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }


}
