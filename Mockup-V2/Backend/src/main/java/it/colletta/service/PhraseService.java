package it.colletta.service;

import it.colletta.model.PhraseModel;
import it.colletta.repository.phrase.PhraseRepository;
import lombok.NonNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhraseService {

    @Autowired
    private PhraseRepository phraseRepository;

    /**
     * returns all the phrased written by a userId
     * 
     * @param userId the id of the user
     * @return List<PhraseModel> the list of the phrases without solution
     */
    public List<PhraseModel> getAllPhrases(@NonNull String userId) {
        return phraseRepository.findAllByAuthor(userId);
    }

}
