package it.colletta.service;

import it.colletta.model.*;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.repository.exercise.ExerciseRepository;
import it.colletta.service.user.UserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        SolutionModel alternativeSolution = SolutionModel.builder()
            .reliability(0)
            .authorId(exercise.getAuthor())
            .solutionText(exercise.getAlternativeSolution())
            .build();

        PhraseModel phrase = PhraseModel.builder()
            .language(exercise.getLanguage())
            .datePhrase(System.currentTimeMillis())
            .phraseText(exercise.getPhraseText())
            .build();

        phrase.addSolution(mainSolution);
        phrase.addSolution(alternativeSolution);
        phrase = phraseService.insertPhrase(phrase);

        ExerciseModel exerciseModel = ExerciseModel.builder()
            .id((new ObjectId().toHexString()))
            .dateExercise(System.currentTimeMillis())
            .phraseReference(phrase)
            .toDo(true)
            .visibilty(exercise.getVisibility())
            .build();

        userService.addExerciseItem(exercise.getAssignedUsersIds(), exerciseModel);
        return exerciseModel;
    }


   /* public String insertExercise(InsertExerciseModel exercise) {

        String phraseId = phraseService
                .insertPhrase(exercise.getTextPhrase())
                .getId();
        PhraseModel phraseModel = phraseService.addSolution(phraseId, exercise.getTextMainSolution(), exercise.getAuthorId());

        if(exercise.getTextAlternativeSolution() != null) {
            phraseModel = phraseService.addSolution(phraseId, exercise.getTextAlternativeSolution(), exercise.getAuthorId());
        }
        ExerciseModel exerciseModel = ExerciseModel.builder()
            .phraseReference(phraseModel)
            .dateExercise(System.currentTimeMillis())
            .visibilty(exercise.getVisibility())
            .build();
        String exerciseId = exerciseRepository.save(exerciseModel).getId();
        userService.addInsertedExercise(exercise.getAuthorId(), exerciseId);
        userService.assignExerciseToUserIds(exerciseId, exercise.getAssignedUsersIds());
        return exerciseId;
    }*/

   /* public String insertExercise(Boolean visibility, String authorId, String phraseId){
        ExerciseModel exercise =
            exerciseRepository.save(
                ExerciseModel.builder()
                    .dateExercise(System.currentTimeMillis())
                    .visibilty(visibility)
                    .phraseReference(phraseId)
                    .build());
        return exercise.getId();
    }

    public Iterable<ExerciseModel> getExercisesToDo(String userId) {
        //TODO CONTROLLO SU userID
        UserModel user = userService.findById(userId).get();

        List<String> toDoExerciseIds = user.getExerciseToDo();
        return exerciseRepository.findAllById(toDoExerciseIds);
    }


	public void getPublicExercises(String userId) {
        Optional<UserModel> user = userService.findById(userId);
        if(user.isPresent()) {
            UserModel userModel = user.get();
            ArrayList<String> exerciseToExclude = new ArrayList<>(userModel.getCompletedExercises());
            exerciseToExclude.addAll(userModel.getExerciseToDo());
            List<ExerciseModel> exerciseListPublic = exerciseRepository.findAllPublicExercises(exerciseToExclude);
            List<String> phraseIds = exerciseListPublic.stream().map(ExerciseModel::getPhraseReference).collect(Collectors.toList());
            List<PhraseModel> phrases = phraseService.getAllPhrasesById(phraseIds);
            //TODO finire
        }
	}

    /*
    public void assignExerciseToClasses(Iterable<ClassModel> classes, String exerciseId){

        // ritorna tutti gli studenti della lista di classi "classes" che ci siamo passati TODO: findAllStudents
        List<String> allClassesStudents = classService.findAllStudents(classes);

        // aggiunge al campo "exerciseToDo" l'exerciseId che ci siamo passati a tutta la lista allClassesStudents TODO: assigExercise
         exerciseRepository.assignExercise(allClassesStudents, exerciseId);
    }
    */
    /*

    return the id of the new exerciseModel
    public String insertExercise(ExerciseModel exercise) {
        PhraseModel phraseModel = null;
        if(exercise.getTextPhrase() != null) {
           Optional<PhraseModel> optionalPhraseModel = phraseRepository.getPhraseWithText(exercise.getTextPhrase());
           if(!optionalPhraseModel.isPresent()) {
                phraseModel = optionalPhraseModel.get();
           }
           else {
            phraseModel = phraseRepository.save(PhraseModel.builder()
                    .phraseText(exercise.getTextPhrase())
                    .datePhrase(Calendar.getInstance().getTime()).build());
           }
           SolutionModel solutionModel = new SolutionModel();
           solutionModel.setSolutionText(exercise.getTextMainSolution());
           solutionModel.setAuthorId(exercise.getAuthor());
           solutionRepository.increaseAffidability(solutionModel);
           phraseModel.addSolution(solutionModel);
           //TODO Guardare affidabilità inserita
           if(exercise.getTextAlternativeSolution() != null) {
               SolutionModel alternativeSolution = new SolutionModel();
               alternativeSolution.setSolutionText(exercise.getTextMainSolution());
               alternativeSolution.setAuthorId(exercise.getAuthor());
               solutionRepository.increaseAffidability(alternativeSolution);
           }
           phraseRepository.save(phraseModel);
           exercise = ExerciseModel.builder().
                   author(exercise.getAuthor())
                   .dateExercise(Calendar.getInstance().getTime()).
                   phraseReference(phraseModel.getId())
                   .visibilty(true).build();
           return String exerciseId = exerciseRepository.save(exercise).getId();
        }


    */
}