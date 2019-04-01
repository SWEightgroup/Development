package it.colletta.service;

import it.colletta.model.*;
import it.colletta.model.helper.CorrectionHelper;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.repository.exercise.ExerciseRepository;
import it.colletta.service.user.UserService;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private PhraseService phraseService;

  @Autowired
  private UserService userService;



  public Optional<ExerciseModel> findById(String id){
    return exerciseRepository.findById(id);
  }



  public ExerciseModel insertExercise(ExerciseHelper exercise) {

    SolutionModel mainSolution = SolutionModel.builder()
        .reliability(0)
        .authorId(exercise.getAuthor())
        .solutionText(exercise.getMainSolution())
        .build();
    SolutionModel alternativeSolution = null;
    if(exercise.getAlternativeSolution() != null) {
      alternativeSolution = SolutionModel.builder()
          .reliability(0)
          .authorId(exercise.getAuthor())
          .solutionText(exercise.getAlternativeSolution())
          .build();
    }

    PhraseModel phrase = PhraseModel.builder()
        .language(exercise.getLanguage())
        .datePhrase(System.currentTimeMillis())
        .phraseText(exercise.getPhraseText())
        .build();

    phrase.addSolution(mainSolution);
    phrase.addSolution(alternativeSolution);
    phrase = phraseService.insertPhrase(phrase);

    Optional<UserModel> user = userService.findById(exercise.getAuthor());
    String teacherName = user.isPresent() ? user.get().getFirstName() + " " + user.get().getLastName() : null;
    ExerciseModel exerciseModel = ExerciseModel.builder()
        .id((new ObjectId().toHexString()))
        .dateExercise(System.currentTimeMillis())
        .mainSolutionReference(mainSolution)
        .alternativeSolutionReference(alternativeSolution)
        .phraseId(phrase.getId())
        .phraseText(exercise.getPhraseText())
        .visibilty(exercise.getVisibility())
        .authorId(exercise.getAuthor())
        .teacherName(teacherName)
        .build();
    userService.addExerciseItem(exercise.getAssignedUsersIds(), exerciseModel);
    exerciseRepository.save(exerciseModel);
    phraseService.increaseReliability(mainSolution);
    phraseService.increaseReliability(alternativeSolution);
    return exerciseModel;
  }
  public List<ExerciseModel> findAllExerciseToDo(String userId) {
    Optional<UserModel> userModel = userService.findById(userId);
    if(userModel.isPresent()) {
      return userModel.get().getExercisesToDo();
    }
    else {
      throw new UsernameNotFoundException("User not found in the system");
    }
  }

  public SolutionModel doExercise(CorrectionHelper correctionHelper) throws Exception {
    Optional<ExerciseModel> exerciseOptional =
        exerciseRepository.findById(correctionHelper.getExerciseId());

    if(exerciseOptional.isPresent()) {
      ExerciseModel exerciseToCorrect = exerciseOptional.get();
      SolutionModel mainSolutionModel = exerciseToCorrect.getMainSolutionReference();
      SolutionModel alternativeSolutionModel = exerciseToCorrect.getAlternativeSolutionReference();
      HashMap<String, String> studentSolutionMap =
          new ObjectMapper().readValue(correctionHelper.getSolutionFromStudent(), HashMap.class);
      HashMap<String, String> mainSolution =
          new ObjectMapper().readValue(mainSolutionModel.getSolutionText(), HashMap.class);

      Double mark = correct(studentSolutionMap, mainSolution);
      if(mark < 10.00) {
        HashMap<String, String> alternativeSolutionMap =
            new ObjectMapper().readValue(mainSolutionModel.getSolutionText(), HashMap.class);
        Double alternativeMark = correct(studentSolutionMap, alternativeSolutionMap);
        if(mark < alternativeMark) {
          mark = alternativeMark;
        }
      }
      Optional<PhraseModel> phraseModel =
          phraseService.getPhraseById(exerciseToCorrect.getPhraseId());
      SolutionModel studentSolution = SolutionModel.builder()
          .dateSolution(System.currentTimeMillis())
          .mark(mark)
          .authorId(correctionHelper.getStudentId())
          .reliability(0)
          .build();
      if(phraseModel.isPresent()) {
        phraseModel.get().addSolution(studentSolution);
        phraseService.insertPhrase(phraseModel.get());
        phraseService.increaseReliability(studentSolution);
      }
      return studentSolution;
    }
    else {
      throw new IllegalArgumentException("Exercise not defined in the system");
    }
  }

  private Double correct(HashMap<String, String> studentSolutionMap, HashMap<String, String> systemSolution) {
    Integer points = 0;
    if(studentSolutionMap.size() == systemSolution.size()) {
      Iterator<String> studentSolutionIterator = studentSolutionMap.values().iterator();
      Iterator<String> mainSolutionIterator = systemSolution.values().iterator();
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
}