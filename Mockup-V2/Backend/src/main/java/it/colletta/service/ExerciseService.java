package it.colletta.service;

import it.colletta.model.ExerciseModel;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;
import it.colletta.service.user.UserService;
import it.colletta.repository.exercise.ExerciseRepository;
import it.colletta.repository.phrase.PhraseRepository;
import it.colletta.repository.solution.SolutionRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
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


    public void insertExercise(ExerciseModel exercise) {

        String phraseId = phraseService.insertPhrase(exercise.getTextPhrase()).getId();

        PhraseModel phraseModel;
        phraseModel = phraseService.addSolution(phraseId, exercise.getTextMainSolution(), exercise.getAuthor());

        if(exercise.getTextAlternativeSolution() != null) {
            phraseModel = phraseService.addSolution(phraseId, exercise.getTextAlternativeSolution(), exercise.getAuthor());
        }

        exercise.setDateExercise(Calendar.getInstance().getTime());
        exercise.setPhraseReference(phraseModel.getId());
        exerciseRepository.save(exercise);
    }


    public Iterable<ExerciseModel> getToDoExercises(String userId) {
        //TODO CONTROLLO SU userID
        UserModel user = userService.findById(userId).get();

        List<String> toDoExerciseIds = user.getExerciseToDo();
        return exerciseRepository.findAllById(toDoExerciseIds);
    }
}