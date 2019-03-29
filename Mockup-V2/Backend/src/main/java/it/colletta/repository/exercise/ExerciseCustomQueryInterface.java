package it.colletta.repository.exercise;

import java.util.List;

import it.colletta.model.ExerciseModel;

public interface ExerciseCustomQueryInterface {

    public List<ExerciseModel> findAllPublicExercises(List<String> exerciseToExclude);
}
