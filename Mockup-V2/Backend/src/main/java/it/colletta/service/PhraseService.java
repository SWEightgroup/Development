package it.colletta.service;

import com.mongodb.client.FindIterable;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.repository.phrase.PhraseRepository;

import lombok.NonNull;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

@Service
public class PhraseService {

  private PhraseRepository phraseRepository;

  @Autowired
  public PhraseService(final PhraseRepository phraseRepository) {
    this.phraseRepository = phraseRepository;
  }

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
  public List<SolutionModel> findAllSolutionsByAuthor(final String authorId) {
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
  public Optional<PhraseModel> getPhraseById(final String phraseId) {
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
  public SolutionModel getSolutionInPhrase(final String phraseId, String solutionId) {
    return phraseRepository.getSolution(phraseId, solutionId);
  }


  public File downloadAllPhrases() throws IOException {
    File file = File.createTempFile("allphrases",".json");
    FindIterable<Document> documents = phraseRepository.findAllPhrasesAsIterable();
    PrintStream fileStream = new PrintStream(file);
        for(Document document : documents) {
          document.remove("_class");
          document.remove("_id");
          fileStream.println(document.toJson());
        }
     return file;
  }
}
