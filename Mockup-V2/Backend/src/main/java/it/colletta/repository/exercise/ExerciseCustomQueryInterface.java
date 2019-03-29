package it.colletta.repository.exercise;

import java.util.List;

public interface ExerciseCustomQueryInterface {

    public List<String> findAllPublicExercises(List<String> exerciseToExclude);
}
