package it.colletta.service;

import it.colletta.model.ExerciseModel;
import it.colletta.repository.PhraseRepository;
import it.colletta.repository.UsersRepository;
import java.util.List;
import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    @Autowired
    private PhraseService phraseService;

    @Autowired
    private SolutionService solutionService;

    public void insertExercise(ExerciseModel exercise) {
        String phraseID = phraseService.insertPhrase(exercise.getTextPhrase());
        
        //String phraseId = phraseService.insertPhrase(exercise.getPhraseText());
        //solutionService.insertSolution(exercise.(), phraseId);
        //solutionService.insertSolution(exercise.getAlternativeSolution(), phraseId);
    
        

        
    }

}