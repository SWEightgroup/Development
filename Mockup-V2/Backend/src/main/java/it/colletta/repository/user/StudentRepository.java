package it.colletta.repository.user;

import it.colletta.model.StudentModel;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends IUserRepository<StudentModel, String> {

  /**
   * Remove the exercise from the student.
   *
   * @param exerciseId the exercise document id
   */
  @Query("{ $pull: { exercisesToDo: ?0 }}")
  void deleteFromExerciseToDo(final String exerciseId);
}
