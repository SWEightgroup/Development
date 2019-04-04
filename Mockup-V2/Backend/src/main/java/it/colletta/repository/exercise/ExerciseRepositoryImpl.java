package it.colletta.repository.exercise;

import java.util.List;
import com.mongodb.client.result.UpdateResult;
import it.colletta.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import it.colletta.model.ExerciseModel;
import org.springframework.data.mongodb.core.query.Update;
import static com.mongodb.client.model.Updates.set;

public class ExerciseRepositoryImpl implements ExerciseCustomQueryInterface {
  @Autowired
  private MongoTemplate mongoTemplate;


  @Override
  public void modifyAuthorExercise(UserModel newUserData, String teacherId) {
    String newTeacherName = newUserData.getFirstName() + " " + newUserData.getLastName();
    Query exerciseToUpdate = new Query(Criteria.where("authorId").is(teacherId));
    Update modify = new Update();
    modify.set("teacherName", newTeacherName);
    mongoTemplate.updateMulti(exerciseToUpdate, modify, ExerciseModel.class);
  }

  @Override
  public List<String> findAllPublicExercises(List<String> exerciseToExclude) {// deve essere Stringa
                                                                              // di Id
    Query query = new Query();
    query.addCriteria(Criteria.where("visibilty").is(true).and("id").nin(exerciseToExclude));
    // query.add
    query.fields().include("exerciseToDo.$id");

    List<ExerciseModel> myExercises = mongoTemplate.find(query, ExerciseModel.class);
    System.out.println("lista esercizi" + myExercises);
    return myExercises;
  }
}
