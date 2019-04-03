package it.colletta.repository.exercise;

import java.util.List;

import it.colletta.model.ExerciseModel;
import it.colletta.model.UserModel;

public interface ExerciseCustomQueryInterface {

    public List<ExerciseModel> findAllPublicExercises(List<ExerciseModel> exerciseToExclude);

    public void modifyAuthorExercise(UserModel newUserData, String teacherId);
}
