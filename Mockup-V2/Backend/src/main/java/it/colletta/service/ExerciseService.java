package it.colletta.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.colletta.model.ExerciseModel;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.CorrectionHelper;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.repository.exercise.ExerciseRepository;
import it.colletta.service.user.UserService;

@Service
public class ExerciseService {

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private PhraseService phraseService;

  @Autowired
  private UserService userService;

  public Optional<ExerciseModel> findById(String id) {
    return exerciseRepository.findById(id);
    // controllo su id, in caso exception
  }

  public void modifyExerciseAuthorName(UserModel newUserData, String token) {
    UserModel oldUser = userService.findByEmail(newUserData.getUsername());
    Optional<String> firstName = Optional.ofNullable(newUserData.getFirstName());
    Optional<String> lastName = Optional.ofNullable(newUserData.getLastName());
    if (firstName.isPresent() && !firstName.get().equals(oldUser.getFirstName())
        || lastName.isPresent() && !lastName.get().equals(oldUser.getLastName())) {
      String authorName = newUserData.getFirstName() + " " + newUserData.getLastName();
      exerciseRepository.modifyAuthorName(authorName, oldUser.getId());
    }
  }

  public ExerciseModel insertExercise(ExerciseHelper exercise) {

    SolutionModel mainSolution = SolutionModel.builder().reliability(0).authorId(exercise.getAuthor())
        .solutionText(exercise.getMainSolution()).build();

    SolutionModel alternativeSolution = null;
    if (exercise != null && !exercise.getAlternativeSolution().isEmpty()) {
      alternativeSolution = SolutionModel.builder().reliability(0).authorId(exercise.getAuthor())
          .solutionText(exercise.getAlternativeSolution()).build();
    }

    PhraseModel phrase = PhraseModel.builder().language(exercise.getLanguage()).datePhrase(System.currentTimeMillis())
        .phraseText(exercise.getPhraseText()).build();

    phrase.addSolution(mainSolution);
    if (alternativeSolution != null && !alternativeSolution.getSolutionText().isEmpty()) {
        phrase.addSolution(alternativeSolution);
      }
    phrase = phraseService.insertPhrase(phrase);

    Optional<UserModel> user = userService.findById(exercise.getAuthor());
    String authorName = user.isPresent() ? user.get().getFirstName() + " " + user.get().getLastName() : null;
    ExerciseModel exerciseModel = ExerciseModel.builder().id((new ObjectId().toHexString()))
        .dateExercise(System.currentTimeMillis()).mainSolutionId(mainSolution.getId())
        .alternativeSolutionId(alternativeSolution != null ? alternativeSolution.getId() : null).phraseId(phrase.getId()).phraseText(exercise.getPhraseText())
        .visibility(exercise.getVisibility()).authorId(exercise.getAuthor()).authorName(authorName).build();
    exerciseRepository.save(exerciseModel);
    phraseService.increaseReliability(mainSolution);
    if (alternativeSolution != null && !alternativeSolution.getSolutionText().isEmpty()) {
      phraseService.increaseReliability(alternativeSolution);
    }
    return exerciseModel;
  }

  /**
   * 
   * Add a phrase solution or free exercise
   * 
   * @author Gionata Legrottaglie
   * @param exercise Exercise/Solution
   * @param userId   Author Id
   * @return This Exercise
   */
  public ExerciseModel insertFreeExercise(ExerciseHelper exercise, String userId) {

    SolutionModel mainSolution = SolutionModel.builder().reliability(0).authorId(exercise.getAuthor())
        .solutionText(exercise.getMainSolution()).build();

    SolutionModel alternativeSolution = null;
    if (exercise != null && !exercise.getAlternativeSolution().isEmpty()) {
      alternativeSolution = SolutionModel.builder().reliability(0).authorId(exercise.getAuthor())
          .solutionText(exercise.getAlternativeSolution()).build();
    }

    PhraseModel phrase = PhraseModel.builder().language(exercise.getLanguage()).datePhrase(System.currentTimeMillis())
        .phraseText(exercise.getPhraseText()).build();

    phrase.addSolution(mainSolution);
    if (alternativeSolution != null && !alternativeSolution.getSolutionText().isEmpty()) {
      phrase.addSolution(alternativeSolution);
    }

    phrase = phraseService.insertPhrase(phrase);

    Optional<UserModel> user = userService.findById(userId);
    String authorName = user.isPresent() ? user.get().getFirstName() + " " + user.get().getLastName() : null;
    ExerciseModel exerciseModel = ExerciseModel.builder().id((new ObjectId().toHexString()))
        .dateExercise(System.currentTimeMillis()).mainSolutionId(mainSolution.getId())
        .alternativeSolutionId(alternativeSolution.getId()).phraseId(phrase.getId()).phraseText(exercise.getPhraseText())
        .visibility(exercise.getVisibility()).authorId(userId).authorName(authorName).build();
    phraseService.increaseReliability(mainSolution);
    if (alternativeSolution != null && !alternativeSolution.getSolutionText().isEmpty()) {
      phraseService.increaseReliability(alternativeSolution);
    }
    return exerciseModel;
  }

  
  public SolutionModel doExercise(CorrectionHelper correctionHelper, String studentId) throws Exception {
	    Optional<ExerciseModel> exerciseOptional =
	        exerciseRepository.findById(correctionHelper.getExerciseId());
	    if(exerciseOptional.isPresent()) {
	      ExerciseModel exerciseToCorrect = exerciseOptional.get();
	      SolutionModel mainSolutionModel =
            phraseService.getSolutionInPhrase(exerciseToCorrect.getPhraseId(),
                exerciseToCorrect.getMainSolutionId());
	      SolutionModel alternativeSolutionModel = null;
	      if(exerciseToCorrect.getAlternativeSolutionId() != null &&
            !exerciseToCorrect.getAlternativeSolutionId().isEmpty()) {
          alternativeSolutionModel = phraseService.getSolutionInPhrase(exerciseToCorrect.getPhraseId(), exerciseToCorrect.getAlternativeSolutionId());
        }
	      ArrayList<String> studentSolutionMap =
	          new ObjectMapper().readValue(correctionHelper.getSolutionFromStudent(), ArrayList.class);
	      ArrayList<String> mainSolution =
	          new ObjectMapper().readValue(mainSolutionModel.getSolutionText(),ArrayList.class);

	      Double mark = correct(studentSolutionMap, mainSolution);
	      if(mark < 10.00 && alternativeSolutionModel != null) {
	        ArrayList<String> alternativeSolutionMap =
	            new ObjectMapper().readValue(alternativeSolutionModel.getSolutionText(), ArrayList.class);
	        if(alternativeSolutionMap != null && !alternativeSolutionMap.isEmpty()) {
	        	Double alternativeMark = correct(studentSolutionMap, alternativeSolutionMap);
		        if(mark < alternativeMark) {
		          mark = alternativeMark;
		        }
	        }
	      }
	      Optional<PhraseModel> phraseModel =
	          phraseService.getPhraseById(exerciseToCorrect.getPhraseId());
	      SolutionModel studentSolution = SolutionModel.builder()
	          .mark(mark)
	          .authorId(studentId)
	          .reliability(0)
	          .solutionText(correctionHelper.getSolutionFromStudent())
	          .build();
	      if(phraseModel.isPresent()) {
	        phraseModel.get().addSolution(studentSolution);
	        phraseService.insertPhrase(phraseModel.get());
	        phraseService.increaseReliability(studentSolution);
	        userService.exerciseCompleted(studentId, exerciseToCorrect);
	      }

	      return studentSolution;
	    }
	    else {
	      throw new IllegalArgumentException("Exercise not defined in the system");
	    }
	  }
  
  private Double correct(ArrayList<String> studentSolutionMap, ArrayList<String> systemSolution) {
	    Integer points = 0;
	    if(studentSolutionMap.size() == systemSolution.size()) {
	      Iterator<String> studentSolutionIterator = studentSolutionMap.iterator();
	      Iterator<String> mainSolutionIterator = systemSolution.iterator();
	      while (mainSolutionIterator.hasNext()) {
	        String word = mainSolutionIterator.next();
	        String studentWord = studentSolutionIterator.next();
	        if(word.equals(studentWord)) {
	          ++points;
	        }
	      }
	    }
	    else {
	      throw new IllegalArgumentException("Solutions have different length");
	    }
	    return (points*10/((double)systemSolution.size()));
	  }
  
  

  public void deleteExercise(String exerciseId) {
    Optional<ExerciseModel> exerciseModelOptional = exerciseRepository.findById(exerciseId);
    if (exerciseModelOptional.isPresent()) {
      ExerciseModel exerciseModel = exerciseModelOptional.get();
      exerciseRepository.delete(exerciseModel);
    }
    // else exception
  }





}
