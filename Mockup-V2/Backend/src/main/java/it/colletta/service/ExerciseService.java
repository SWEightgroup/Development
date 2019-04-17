package it.colletta.service;

import it.colletta.model.ExerciseModel;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.CorrectionHelper;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.repository.exercise.ExerciseRepository;
import it.colletta.service.user.UserService;
import it.colletta.security.Role;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private PhraseService phraseService;

  @Autowired
  private UserService userService;

  @Autowired
  private SolutionService solutionService;

  /**
   * Constructor.
   *
   * @param exerciseRepository exerciseRepository
   */
  ExerciseService(final ExerciseRepository exerciseRepository, final PhraseService phraseService
                 ,final UserService userService, final SolutionService solutionService) {

    this.exerciseRepository = exerciseRepository;
    this.phraseService = phraseService;
    this.userService = userService;
    this.solutionService = solutionService;
  }

  /**
   * Find by id.
   *
   * @param id Exercise id
   * @returnc Exericse
   */
  public Optional<ExerciseModel> findById(final String id) {
    return exerciseRepository.findById(id);
    // controllo su id, in caso exception
  }

  /**
   * Modify exercise author name.
   *
   * @param newUserData User info
   * @param token       User token
   */
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

  /**
   * Insert new exercise in the system.
   *
   * @param exercise New Exercise
   * @return Exercise
   */
  public ExerciseModel insertExercise(ExerciseHelper exercise) {

    PhraseModel phrase = phraseService.createPhrase(exercise.getPhraseText(), exercise.getLanguage());

    SolutionModel mainSolution = solutionService.createSolution(exercise.getMainSolution(), exercise.getAuthor());
    SolutionModel alternativeSolution = solutionService.createSolution(exercise.getAlternativeSolution(),
        exercise.getAuthor());

    phrase.addSolution(mainSolution);
    if (alternativeSolution != null) {
      phrase.addSolution(alternativeSolution);
    }

    phrase = phraseService.insertPhrase(phrase);

    Optional<UserModel> userOpt = userService.findById(exercise.getAuthor());
    UserModel user = userOpt.get();
    String authorName = user.getFirstName() + " " + user.getLastName();

    ExerciseModel exerciseModel = ExerciseModel.builder().id((new ObjectId().toHexString()))
        .dateExercise(System.currentTimeMillis()).mainSolutionId(mainSolution.getId())
        .alternativeSolutionId(alternativeSolution != null ? alternativeSolution.getId() : null)
        .phraseId(phrase.getId()).phraseText(exercise.getPhraseText()).visibility(exercise.getVisibility())
        .authorId(exercise.getAuthor()).authorName(authorName).build();

    exerciseRepository.save(exerciseModel);
    userService.addExerciseItem(exercise.getAssignedUsersIds(), exerciseModel);

    return exerciseModel;
  }

  /**
   * Add a phrase solution or free exercise.
   *
   * @param exercise Exercise/Solution
   * @param userId   Author Id
   * @return This Exercise
   * @author Gionata Legrottaglie
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
        // .alternativeSolutionId(alternativeSolution.getId())
        .phraseId(phrase.getId()).phraseText(exercise.getPhraseText()).visibility(exercise.getVisibility())
        .authorId(userId).authorName(authorName).build();
    phraseService.increaseReliability(mainSolution);
    if (alternativeSolution != null && !alternativeSolution.getSolutionText().isEmpty()) {
      phraseService.increaseReliability(alternativeSolution);
    }
    return exerciseModel;
  }

  /**
   * Add solution in the system and returns it with the mark.
   *
   * @param correctionHelper New solution
   * @param studentId        Student id
   * @return Solution Solution with mark
   * @throws Exception Exception
   */
  public SolutionModel doExercise(CorrectionHelper correctionHelper, String studentId) throws Exception {
    Optional<ExerciseModel> exerciseOptional = exerciseRepository.findById(correctionHelper.getExerciseId());
    if (exerciseOptional.isPresent()) {
      ExerciseModel exerciseToCorrect = exerciseOptional.get();
      SolutionModel mainSolutionModel = phraseService.getSolutionInPhrase(exerciseToCorrect.getPhraseId(),
          exerciseToCorrect.getMainSolutionId());
      SolutionModel alternativeSolutionModel = null;
      if (exerciseToCorrect.getAlternativeSolutionId() != null
          && !exerciseToCorrect.getAlternativeSolutionId().isEmpty()) {
        alternativeSolutionModel = phraseService.getSolutionInPhrase(exerciseToCorrect.getPhraseId(),
            exerciseToCorrect.getAlternativeSolutionId());
      }
      ArrayList<String> studentSolutionMap = new ObjectMapper().readValue(correctionHelper.getSolutionFromStudent(),
          ArrayList.class);
      ArrayList<String> mainSolution = new ObjectMapper().readValue(mainSolutionModel.getSolutionText(),
          ArrayList.class);

      Double mark = correct(studentSolutionMap, mainSolution);
      if (mark < 10.00 && alternativeSolutionModel != null) {
        ArrayList<String> alternativeSolutionMap = new ObjectMapper()
            .readValue(alternativeSolutionModel.getSolutionText(), ArrayList.class);
        if (alternativeSolutionMap != null && !alternativeSolutionMap.isEmpty()) {
          Double alternativeMark = correct(studentSolutionMap, alternativeSolutionMap);
          if (mark < alternativeMark) {
            mark = alternativeMark;
          }
        }
      }
      Optional<PhraseModel> phraseModel = phraseService.getPhraseById(exerciseToCorrect.getPhraseId());
      SolutionModel studentSolution = SolutionModel.builder().mark(mark).authorId(studentId).reliability(0)
          .solutionText(mainSolutionModel.getSolutionText()).build();
      if (phraseModel.isPresent()) {
        phraseModel.get().addSolution(studentSolution);
        phraseService.insertPhrase(phraseModel.get());
        phraseService.increaseReliability(studentSolution);
        userService.exerciseCompleted(studentId, exerciseToCorrect);
      }

      return studentSolution;
    } else {
      throw new IllegalArgumentException("Exercise not defined in the system");
    }
  }

  /**
   * Return the mark.
   *
   * @param studentSolutionMap List of student solutions
   * @param systemSolution     Main of alternative solution
   * @return Mark
   */
  private Double correct(ArrayList<String> studentSolutionMap, ArrayList<String> systemSolution) {
    Integer points = 0;
    if (studentSolutionMap.size() == systemSolution.size()) {
      Iterator<String> studentSolutionIterator = studentSolutionMap.iterator();
      Iterator<String> mainSolutionIterator = systemSolution.iterator();
      while (mainSolutionIterator.hasNext()) {
        String word = mainSolutionIterator.next();
        String studentWord = studentSolutionIterator.next();
        if (word.equals(studentWord)) {
          ++points;
        }
      }
    } else {
      throw new IllegalArgumentException("Solutions have different length");
    }
    return (points * 10 / ((double) systemSolution.size()));
  }

  /**
   * Delete exercise by id.
   *
   * @param exerciseId Exercise id
   */
  public void deleteExercise(final String exerciseId) {
    Optional<ExerciseModel> exerciseModelOptional = exerciseRepository.findById(exerciseId);
    if (exerciseModelOptional.isPresent()) {
      ExerciseModel exerciseModel = exerciseModelOptional.get();
      exerciseRepository.delete(exerciseModel);
    }
    // else exception
  }

  /**
   * getAllByIds.
   *
   * @param id Student id
   * @return List of exericse
   */
  public Iterable<ExerciseModel> getAllDoneByAuthorId(final String id) {
    List<String> exercisesDoneId = userService.getAllExerciseDone(id);
    return exerciseRepository.findAllById(exercisesDoneId);
  }

  public Iterable<ExerciseModel> getAllToDoByAuthorId(final String id) {
    List<String> exercisesToDoId = userService.getAllExerciseToDo(id);
    return exerciseRepository.findAllById(exercisesToDoId);
  }

  /**
   * 
   * @param page
   * @param userId
   * @return
   */
  public Page<ExerciseModel> getAllDoneByAuthorId(final Pageable page, final String userId) {

    List<String> exercisesDoneid = userService.getAllExerciseDone(userId);
    return exerciseRepository.findAllByIdPaged(page,
        exercisesDoneid.stream().map(ObjectId::new).collect(Collectors.toList()));

  }

  /**
   * 
   * @param page
   * @param userId
   * @return
   */
  public Page<ExerciseModel> getAllAddedByAuthorId(final Pageable page, final String userId) {
    return exerciseRepository.findAllByAuthorIdPaged(page, userId);
  }

  public Page<ExerciseModel> getAllToDoByAuthorId(final Pageable page, final String id) {
    List<String> exercisesToDoId = userService.getAllExerciseToDo(id);
    List<ObjectId> ids = exercisesToDoId.stream().map(ObjectId::new).collect(Collectors.toList());
    final Page<ExerciseModel> allByIdPaged = exerciseRepository.findAllByIdPaged(page, ids);
    return allByIdPaged;
  }

}
