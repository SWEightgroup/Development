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

  @Autowired private PhraseRepository phraseRepository;

  /**
   * returns all the phrased written by a userId.
   *
   * @param userId the id of the user
   * @return the list of the phrases without solution
   */
  public List<PhraseModel> getAllPhrases(@NonNull String userId) { // TODO che senso ha?
    return phraseRepository.findAllByAuthor(userId);
  }

  /**
   * Insert a new phrase in the system.
   *
   * @param phraseModel Phrase
   * @return Phrase
   */
  public PhraseModel insertPhrase(PhraseModel phraseModel) {
    Optional<PhraseModel> phraseOptional =
        phraseRepository.getPhraseWithText(phraseModel.getPhraseText());
    if (phraseOptional.isPresent()) {
      PhraseModel phrase = phraseOptional.get();
      phrase.setSolutions(phraseModel.getSolutions());
      return phraseRepository.save(phrase);
    } else {
      return phraseRepository.save(phraseModel);
    }
  }

  /**
   * Find all solution by Author id.
   *
   * @param authorId Author id
   * @return List of Solution
   */
  public List<SolutionModel> findAllSolutionsByAuthor(String authorId) {
    return phraseRepository.findAllSolutionsByAuthor(authorId);
  }

  /**
   * Get all phrase by id.
   *
   * @param phraseIds List of phrases id
   * @return LIst of Phrases
   */
  public List<PhraseModel> getAllPhrasesById(List<String> phraseIds) {
    // return phraseRepository.findAllPhrasesByIds(phraseIds);
    return null;
  }

  /**
   * Get phrase by id.
   *
   * @param phraseId Phrase id
   * @return Phrase
   */
  public Optional<PhraseModel> getPhraseById(String phraseId) {
    return phraseRepository.findById(phraseId);
  }

  /**
   * Increase Realibility of the phrase.
   *
   * @param mainSolution main solution
   */
  public void increaseReliability(SolutionModel mainSolution) {
    phraseRepository.increaseReliability(mainSolution);
  }

  /**
   * Get solution of the phrase by id.
   *
   * @param phraseId Phrase id
   * @param solutionId Solution id
   * @return Solution
   */
  public SolutionModel getSolutionInPhrase(String phraseId, String solutionId) {
    return phraseRepository.getSolution(phraseId, solutionId);
  }
}
