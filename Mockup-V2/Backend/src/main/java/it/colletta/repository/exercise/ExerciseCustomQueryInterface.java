package it.colletta.repository.exercise;

import it.colletta.model.UserModel;

import java.util.List;

public interface ExerciseCustomQueryInterface {

    public void assignExercise(List<UserModel> allClassesStudents, String exerciseId);

}
