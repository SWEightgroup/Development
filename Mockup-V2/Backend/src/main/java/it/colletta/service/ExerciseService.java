package it.colletta.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import it.colletta.model.*;
import it.colletta.repository.classes.ClassRepository;
import it.colletta.repository.exercise.ExerciseRepository;
import it.colletta.repository.phrase.PhraseRepository;
import it.colletta.repository.solution.SolutionRepository;
import it.colletta.service.classes.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private PhraseService phraseService;

    @Autowired
    private ClassService classService;


    public String insertExercise(ExerciseModel exercise) {

        String phraseId = phraseService.insertPhrase(exercise.getTextPhrase()).getId();

        PhraseModel phraseModel;
        phraseModel = phraseService.addSolution(phraseId, exercise.getTextMainSolution(), exercise.getAuthor());

        if(exercise.getTextAlternativeSolution() != null) {
            phraseModel = phraseService.addSolution(phraseId, exercise.getTextAlternativeSolution(), exercise.getAuthor());
        }

        exercise.setDateExercise(System.currentTimeMillis());
        exercise.setPhraseReference(phraseModel.getId());
        return exerciseRepository.save(exercise).getId();
    }

    public String insertExercise(Boolean visibility, String authorId, String phraseId){
        ExerciseModel exercise =
            exerciseRepository.save(
                ExerciseModel.builder()
                    .dateExercise(System.currentTimeMillis())
                    .author(authorId)
                    .visibilty(visibility)
                    .phraseReference(phraseId)
                    .build());
        return exercise.getId();
    }

    public void assignExerciseToClasses(Iterable<ClassModel> classes, String exerciseId){

        // ritorna tutti gli studenti della lista di classi "classes" che ci siamo passati TODO: findAllStudents
        List<UserModel> allClassesStudents = classService.findAllStudents(classes);

        // aggiunge al campo "exerciseToDo" l'exerciseId che ci siamo passati a tutta la lista allClassesStudents TODO: assigExercise
        return exerciseRepository.assignExercise(allClassesStudents, exerciseId);
    }

    /*
    public void insertExercise(ExerciseModel exercise) {
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
           exerciseRepository.save(exercise);
        }

        
    }
    */

}