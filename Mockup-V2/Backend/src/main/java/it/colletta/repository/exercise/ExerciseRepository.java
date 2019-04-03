package it.colletta.repository.exercise;

import it.colletta.model.ExerciseModel;
import it.colletta.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;


public interface ExerciseRepository extends MongoRepository<ExerciseModel, String> {

  @Override
  Iterable<ExerciseModel> findAllById(Iterable<String> ids);

  @Override
  public Optional<ExerciseModel> findById(String id);

  public List<ExerciseModel> findAllPublicExercises(List<ExerciseModel> userId);


  public void modifyAuthorExercise(UserModel newUserData, String teacherId);

}
