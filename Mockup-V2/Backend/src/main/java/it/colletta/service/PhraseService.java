package it.colletta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.repository.phrase.PhraseRepository;
import lombok.NonNull;


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
    public List<PhraseModel> getAllPhrases(@NonNull String userId) { //TODO che senso ha?
        return phraseRepository.findAllByAuthor(userId);
    }

    public PhraseModel insertPhrase(PhraseModel phraseModel) {
        Optional<PhraseModel> phraseOptional = phraseRepository.getPhraseWithText(phraseModel.getPhraseText());
        if(phraseOptional.isPresent()) {
            PhraseModel phrase = phraseOptional.get();
            ArrayList<SolutionModel> solutions = phrase.getSolutions();
            solutions.addAll(phraseModel.getSolutions());
            phrase.setSolutions(solutions);
            return phraseRepository.save(phrase);
        }
        else {
            return phraseRepository.save(phraseModel);
        }
    }

    public List<SolutionModel> findAllSolutionsByAuthor(String authorId) {
        return phraseRepository.findAllSolutionsByAuthor(authorId);
    }

    public List<PhraseModel> getAllPhrasesById(List<String> phraseIds) {
        //return phraseRepository.findAllPhrasesByIds(phraseIds);
        return null;
    }
    
    public Optional<PhraseModel> getPhraseById(String phraseId) {
        return phraseRepository.findById(phraseId);
  }

	public void increaseReliability(SolutionModel mainSolution) {
        phraseRepository.increaseReliability(mainSolution);
	}
	
	public PhraseModel getSolutionInPhrase(String phraseId,String solutionId){
		return phraseRepository.getSolution(phraseId, solutionId);
	}
}
