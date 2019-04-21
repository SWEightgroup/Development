package it.colletta.repository.user;

import it.colletta.model.TeacherModel;

public interface TeacherRepository extends IUserRepository<TeacherModel, String> {

    //TODO query apposita
    void removeFromTeacherExercise(String exerciseId);
}
