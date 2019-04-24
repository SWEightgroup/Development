package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.ExerciseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends MongoRepository<ExerciseModel, String>,
    ExerciseCustomQueryInterface {

  UpdateResult modifyAuthorName(final String newAuthorName, String teacherId);
  Page<ExerciseModel> findExerciseModelsByStudentIdDoneIsIn(Pageable page, String id);
  Page<ExerciseModel> findExerciseModelsByStudentIdToDoIsIn(Pageable page, String id);

  @Query("{ $pull: { studentIdToDo : ?0} }")
  void pullStudentToDoList(String studentId);

  @Query("{ $push: { studentIdDone : ?0} }")
  void pushStudentDoneList(String studentId);

  @Query(value = "{ $and: [{visibility: true}, {$studentIdToDo : {$nin: ?0} }, {$studentIdDone : {$nin: ?0}}]}")
  Page<ExerciseModel> findPublicExercise(String studentId, Pageable page);
}
