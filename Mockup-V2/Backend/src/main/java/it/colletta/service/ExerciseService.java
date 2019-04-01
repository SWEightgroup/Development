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
        exerciseRepository.save(exerciseModel);
        phraseService.increaseReliability(mainSolution);
        phraseService.increaseReliability(alternativeSolution);
        return exerciseModel;
    }
}