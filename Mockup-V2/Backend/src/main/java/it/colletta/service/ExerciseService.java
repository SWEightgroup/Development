package it.colletta.service;

import it.colletta.model.ExerciseModel;
import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.repository.ExerciseRepository;
import it.colletta.repository.PhraseRepository;
import it.colletta.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private PhraseRepository phraseRepository;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    public void insertExercise(ExerciseModel exercise) {
        //String phraseID = phraseService.insertPhrase(exercise.getTextPhrase());
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

}