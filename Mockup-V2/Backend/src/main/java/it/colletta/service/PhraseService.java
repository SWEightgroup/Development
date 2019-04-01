package it.colletta.service;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.repository.phrase.PhraseRepository;
import java.util.List;
import java.util.Optional;

import lombok.NonNull;
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
    public List<PhraseModel> getAllPhrases(@NonNull String userId) { //TODO che senso ha?
        return phraseRepository.findAllByAuthor(userId);
    }

    public PhraseModel insertPhrase(PhraseModel phraseModel) {
        Optional<PhraseModel> phrase = phraseRepository.getPhraseWithText(phraseModel.getPhraseText());
        if(phrase.isPresent()) {
            phrase.get().getSolutions().addAll(phraseModel.getSolutions());
            return phrase.get();
        }
        else {
            return phraseRepository.save(phraseModel);
        }
    }

    public PhraseModel addSolution(String phraseId, SolutionModel solutionModel) {

        PhraseModel phraseToReturn = null;

        Optional<PhraseModel> phraseModelOptional = phraseRepository.findById(phraseId);
        if(phraseModelOptional.isPresent())
        {
            PhraseModel phraseModel = phraseModelOptional.get();
            phraseModel.addSolution(solutionModel);
            phraseToReturn = insertPhrase(phraseModel);
        }
        //TODO controlli se phraseId non corrisponde a una frase nel db
        return phraseToReturn;
    }

    public PhraseModel addSolution(String phraseId, String solutionText, String authorId) {
        SolutionModel solutionModel = SolutionModel.builder()
            .solutionText(solutionText)
            .authorId(authorId)
            .dateSolution(System.currentTimeMillis())
            .reliability(0)
            .build();
        return addSolution(phraseId, solutionModel);
    }

    public List<SolutionModel> findAllSolutionsByAuthor(String authorId) {
        return phraseRepository.findAllSolutionsByAuthor(authorId);
    }

    public List<PhraseModel> getAllPhrasesById(List<String> phraseIds) {
        //return phraseRepository.findAllPhrasesByIds(phraseIds);
        return null;
    }


}
