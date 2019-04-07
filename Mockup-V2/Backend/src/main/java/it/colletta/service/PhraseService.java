package it.colletta.service;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.repository.phrase.PhraseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
  public List<PhraseModel> getAllPhrases(@NonNull String userId) { // TODO che senso ha?
    return phraseRepository.findAllByAuthor(userId);
  }

  /**
   * 
   * @param phraseModel
   * @return
   */
  public PhraseModel insertPhrase(PhraseModel phraseModel) {
    Optional<PhraseModel> phraseOptional =
        phraseRepository.getPhraseWithText(phraseModel.getPhraseText());
    if (phraseOptional.isPresent()) {
      PhraseModel phrase = phraseOptional.get();
      ArrayList<SolutionModel> solutions = phrase.getSolutions();
      phrase.setSolutions(phraseModel.getSolutions());
      return phraseRepository.save(phrase);
    } else {
      return phraseRepository.save(phraseModel);
    }
  }

  /**
   * 
   * @param authorId
   * @return
   */
  public List<SolutionModel> findAllSolutionsByAuthor(String authorId) {
    return phraseRepository.findAllSolutionsByAuthor(authorId);
  }

  /**
   * 
   * @param phraseIds
   * @return
   */
  public List<PhraseModel> getAllPhrasesById(List<String> phraseIds) {
    // return phraseRepository.findAllPhrasesByIds(phraseIds);
    return null;
  }

  /**
   * 
   * @param phraseId
   * @return
   */
  public Optional<PhraseModel> getPhraseById(String phraseId) {
    return phraseRepository.findById(phraseId);
  }

  /**
   * 
   * @param mainSolution
   */
  public void increaseReliability(SolutionModel mainSolution) {
    phraseRepository.increaseReliability(mainSolution);
  }

  /**
   * 
   * @param phraseId
   * @param solutionId
   * @return
   */
  public SolutionModel getSolutionInPhrase(String phraseId, String solutionId) {
    return phraseRepository.getSolution(phraseId, solutionId);
  }
}
