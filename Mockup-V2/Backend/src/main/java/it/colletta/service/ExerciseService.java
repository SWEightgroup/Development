package it.colletta.service;

import it.colletta.model.*;
import it.colletta.model.helper.ExerciseHelper;
import it.colletta.repository.exercise.ExerciseRepository;
import it.colletta.service.user.UserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            .mainSolutionReference(mainSolution)
            .alternativeSolutionReference(alternativeSolution)
            //.phraseReference(phrase)
            .phraseId(phrase.getId())
            .toDo(true)
            .visibilty(exercise.getVisibility())
            .build();

        userService.addExerciseItem(exercise.getAssignedUsersIds(), exerciseModel);
        return exerciseModel;
    }
    public List<ExerciseModel> findAllExerciseToDo(String userId) {
        Optional<UserModel> userModel = userService.findById(userId);
        if(userModel.isPresent()) {
            return userModel.get().getExercises().stream().filter(exerciseModel -> exerciseModel.getToDo() == true).collect(
                Collectors.toList());
        }
        else {
            throw new UsernameNotFoundException("User not found in the system");
        }
    }

}