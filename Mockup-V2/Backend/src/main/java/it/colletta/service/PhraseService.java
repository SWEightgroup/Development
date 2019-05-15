package it.colletta.service;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.helper.FilterHelper;
import it.colletta.repository.phrase.PhraseRepository;

import lombok.NonNull;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

@Service
public class PhraseService {

  private PhraseRepository phraseRepository;

  /**
   * Constructor.
   *
   * @param phraseRepository phraseRepository.
   */
  @Autowired
  public PhraseService(final PhraseRepository phraseRepository) {
    this.phraseRepository = phraseRepository;
  }

  /**
   * returns all the phrased written by a userId.
   *
   * @param userId the id of the user.
   * @return the list of the phrases without solution.
   */
  public List<PhraseModel> getAllPhrases(@NonNull String userId) {
    return phraseRepository.findAllByAuthor(userId);
  }

  /**
   * Insert a new phrase in the system.
   *
   * @param newPhrase Phrase.
   * @return Phrase.
   */
  public PhraseModel insertPhrase(PhraseModel newPhrase) {

    PhraseModel returnPhrase;
    Optional<PhraseModel> phraseOptional = phraseRepository.findPhraseModelByPhraseTextIs(newPhrase.getPhraseText());
    if (phraseOptional.isPresent()) {
      PhraseModel phrase = phraseOptional.get();

      for (SolutionModel solution : newPhrase.getSolutions()) {
        phrase.addSolution(solution);
      }

      returnPhrase = phraseRepository.save(phrase);
    } else {
      returnPhrase = phraseRepository.save(newPhrase);
    }

    for (SolutionModel solution : newPhrase.getSolutions()) {
      increaseReliability(solution);
    }
    return returnPhrase;

  }

  /**
   * Save a phrase in the system.
   *
   * @param phrase Phrase.
   */
  public void savePhrase(PhraseModel phrase) {
    phraseRepository.save(phrase);
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
   * @param phraseIds List of phrases id.
   * @return LIst of Phrases.
   */
  public List<PhraseModel> getAllPhrasesById(List<String> phraseIds) {
    // return phraseRepository.findAllPhrasesByIds(phraseIds);
    return null;
  }

  /**
   * Get phrase by id.
   *
   * @param phraseId Phrase id.
   * @return Phrase.
   */
  public Optional<PhraseModel> getPhraseById(final String phraseId) {
    return phraseRepository.findById(phraseId);
  }

  /**
   * Increase Realibility of the phrase.
   *
   * @param mainSolution main solution.
   */
  public void increaseReliability(SolutionModel mainSolution) {

    phraseRepository.increaseReliability(mainSolution);
  }

  /**
   * Get solution of the phrase by id.
   *
   * @param phraseId   Phrase id.
   * @param solutionId Solution id.
   * @return Solution.
   */
  public SolutionModel getSolutionInPhrase(final String phraseId, String solutionId, String authorId) {
    return phraseRepository.getSolution(phraseId, solutionId);
  }

  /**
   * downloadPhrasesWithFilter.
   * 
   * @param filter Filters
   * @return File.
   * @throws IOException IOException
   */
  public File downloadPhrasesWithFilter(FilterHelper filter) throws IOException {
    File file = File.createTempFile("filteredPhrases", ".json");
    List<Document> documents = phraseRepository.findAllPhrasesWithFilter(filter);
    PrintStream fileStream = new PrintStream(file);

    for (Document document : documents) {
      document.remove("_class");
      document.remove("_id");
      fileStream.print(document.toJson());
      fileStream.print(",");
    }
    fileStream.close();
    return file;
  }

  /**
   * downloadAllPhrases.
   * 
   * @return File
   * @throws IOException IOException
   */
  public File downloadAllPhrases() throws IOException {
    File file = File.createTempFile("allphrases", ".json");
    List<Document> documents = phraseRepository.findAllPhrases();
    PrintStream fileStream = new PrintStream(file);
    for (Document document : documents) {
      document.remove("_class");
      document.remove("_id");
      fileStream.append(document.toJson(new JsonWriterSettings(true)));
      fileStream.println(",");
    }
    fileStream.close();
    return file;
  }

  /**
   * createPhrase.
   * 
   * @param phraseText Text
   * @param language   language
   * @return PhraseModel
   */
  public PhraseModel createPhrase(String phraseText, String language) {
    return PhraseModel.builder().language(language).datePhrase(System.currentTimeMillis()).phraseText(phraseText)
        .build();

  }
}
