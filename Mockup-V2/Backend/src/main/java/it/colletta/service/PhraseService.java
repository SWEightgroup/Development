package it.colletta.service;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.repository.phrase.PhraseRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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

    public PhraseModel insertPhrase(PhraseModel phraseModel) {
	    return phraseRepository.save(phraseModel);
    }

    public PhraseModel insertPhrase(String phrase) {
        PhraseModel phraseModel;
        Optional<PhraseModel> optionalPhraseModel = phraseRepository.getPhraseWithText(phrase);
        if(optionalPhraseModel.isPresent()) {
            phraseModel = optionalPhraseModel.get();
        }
        else {
            phraseModel = insertPhrase(PhraseModel.builder()
                    .phraseText(phrase)
                    .datePhrase(Calendar.getInstance().getTime()).build());
        }
        return phraseModel;
    }


    public PhraseModel addSolution(String phraseId, SolutionModel solutionModel) {
	    //TODO CONTI SU AFFIDABILITA
        PhraseModel phraseToReturn = null;

        Optional<PhraseModel> phraseModelOptional = phraseRepository.findById(phraseId);
        if(phraseModelOptional.isPresent())
        {
            PhraseModel phraseModel = phraseModelOptional.get();
            phraseModel.addSolution(solutionModel);
            phraseToReturn = phraseRepository.insert(phraseModel);
        }
        //TODO controlli se phraseId non corrisponde a una frase nel db
        return phraseToReturn;
    }

    public PhraseModel addSolution(String phraseId, String solutionText, String authorId) {
	    //TODO scegliere se usare builder o costruttore
        SolutionModel solutionModel = new SolutionModel(solutionText,authorId);
        return addSolution(phraseId,solutionModel);
    }

}
