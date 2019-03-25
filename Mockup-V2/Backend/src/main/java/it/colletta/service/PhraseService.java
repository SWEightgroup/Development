package it.colletta.service;

import it.colletta.model.PhraseModel;
import it.colletta.repository.PhraseRepository;
import it.colletta.repository.UsersRepository;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhraseService {

    @Autowired
    private PhraseRepository phraseRepository;

    @Autowired
    private UsersRepository usersRepository;

    /**
     * @param phrase The phrase which will be inserted
     * @return 0L if the phrase was been inserted,
     * otherwise return another number
     */
    public Long insertPhrase(PhraseModel phrase) {
        Long numberOfPhrase =
            phraseRepository.countPhrasesWithText(phrase.getPhraseText());
        if(numberOfPhrase == 0L) {
            phraseRepository.save(phrase); //NOT GOOD
        }

        
        return numberOfPhrase;
    }


    /**
     * @param userId the 
     * @return List<PhraseModel> 
     */
	public List<PhraseModel> getAllPhrases(String userId) {
        List<String> ids = usersRepository.findAllPhrasesInserted(userId);
        Iterable<PhraseModel> phraseModel = phraseRepository.findAllById(ids);
        return org.apache.commons.collections4.IteratorUtils.toList(phraseModel.iterator());
    }


}
