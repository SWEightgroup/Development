package it.colletta.service;

import it.colletta.model.PhraseModel;
import it.colletta.repository.PhraseRepository;
import it.colletta.repository.UsersRepository;
import java.util.List;
import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhraseService {

    @Autowired
    private PhraseRepository phraseRepository;

    /**
     * @param phrase The phrase which will be inserted
     * @return String : 
     */
    public String insertPhrase(String phrase) {
        Long numberOfPhrase =
            phraseRepository.countPhrasesWithText(phrase);
        PhraseModel insertedPhrase;
        if(numberOfPhrase == 0L) {
            insertedPhrase = phraseRepository.save(
                PhraseModel.builder().phraseText(phrase).build()
                );
        }
        else {
            insertedPhrase = phraseRepository.getPhraseWithText(phrase);
        }
        return insertedPhrase.getId();
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
