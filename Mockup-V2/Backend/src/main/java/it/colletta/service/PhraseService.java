package it.colletta.service;

import it.colletta.model.PhraseModel;
import it.colletta.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhraseService {

    @Autowired
    private PhraseRepository phraseRepository;


    /**
     * @param phrase The phrase which will be inserted
     * @return 0L if the phrase was been inserted,
     * otherwise return another number
     */
    public Long insertPhrase(PhraseModel phrase) {
        Long numberOfPhrase =
            phraseRepository.countPhrasesWithText(phrase.getText());
        if(numberOfPhrase == 0L) {
            phraseRepository.save(phrase); //NOT GOOD
        }
        return numberOfPhrase;
    }


}
