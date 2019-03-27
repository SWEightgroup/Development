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
     * TODO DAMIEN
     * @param userId the 
     * @return List<PhraseModel> 
     */
	public List<PhraseModel> getAllPhrases(String userId) {
	    phraseRepository.findAllByAuthor(userId);
        //List<String> ids = usersRepository
        //W<PhraseModel> phraseModel = phraseRepository.findAllById(userId);
        //return org.apache.commons.collections4.IteratorUtils.toList(phraseModel.iterator());
        return null;
    }


}
