package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;

import it.colletta.model.ExerciseModel;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class ExerciseRepositoryImpl implements ExerciseCustomQueryInterface {

  private MongoTemplate mongoTemplate;

  @Autowired
  public ExerciseRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public UpdateResult modifyAuthorName(final String newAuthorName, String authorId) {
    Query exerciseToUpdate = new Query(Criteria.where("authorId").is(authorId));
    Update modify = new Update();
    modify.set("authorName", newAuthorName);
    return mongoTemplate.updateMulti(exerciseToUpdate, modify, ExerciseModel.class);
  }

  @Override
  public Page<ExerciseModel> findByIdPaged(Pageable page, List<ObjectId> ids) {
    Query query = new Query(Criteria.where("id").in(ids)).with(page);
    List<ExerciseModel> exerciseModelList = mongoTemplate.find(query, ExerciseModel.class);
    long total = mongoTemplate.count(query, ExerciseModel.class);
    return new PageImpl<>(exerciseModelList, page, total);
  }

  @Override
  public Page<ExerciseModel> findPublicByIdPaged(Pageable page, List<ObjectId> ids) {
    Query query = new Query(Criteria.where("authorId").nin(ids)).with(page);
    List<ExerciseModel> exerciseModelList = mongoTemplate.find(query, ExerciseModel.class);
    long total = mongoTemplate.count(query, ExerciseModel.class);
    return new PageImpl<>(exerciseModelList, page, total);
  }

  @Override
  public Page<ExerciseModel> findByAuthorIdPaged(Pageable page, String id) {
    Query query = new Query(Criteria.where("authorId").is(id)).with(page);
    List<ExerciseModel> exerciseModelList = mongoTemplate.find(query, ExerciseModel.class);
    long total = mongoTemplate.count(query, ExerciseModel.class);
    return new PageImpl<>(exerciseModelList, page, total);
  }

  /**
   * pullStudentToDoList.
   */
  public void pullStudentToDoList(String exerciseId, String studentId) {
    Query query = new Query(Criteria.where("_id").is(new ObjectId(exerciseId)));
    Update update = new Update().pull("studentIdToDo", studentId);
    mongoTemplate.updateMulti(query, update, ExerciseModel.class);
  }

  /**
   * pushStudentDoneList.
   */
  public void pushStudentDoneList(String exerciseId, String studentId) {
    Query query = new Query(Criteria.where("_id").is(new ObjectId(exerciseId)));
    Update update = new Update().push("studentIdDone", studentId);
    mongoTemplate.updateMulti(query, update, ExerciseModel.class);
  }

  @Override
  public Page<ExerciseModel> findAllPublicExercises(Pageable page, String studentId) {
    Query query = new Query(Criteria.where("studentIdDone").nin(studentId).and("studentIdToDo")
        .nin(studentId).and("visibility").is(true)).with(page);
    return new PageImpl<>(mongoTemplate.find(query, ExerciseModel.class), page,
        mongoTemplate.count(query, ExerciseModel.class));
  }

  /**
   * Return all exercises done by a student.
   *
   * @param page {@link Pageable}
   * @param studentId the student unique id
   * @param teacherFavoriteIds the List of the user favorite teachers
   * @return All public exercise of user favorite teacher
   */
  @Override
  public Page<ExerciseModel> findAllFavoriteExercises(Pageable page,
      List<String> teacherFavoriteIds, String studentId) {
    Query query =
        new Query(Criteria.where("studentIdDone").nin(studentId).and("studentIdToDo").nin(studentId)
            .and("visibility").is(true).and("authorId").in(teacherFavoriteIds)).with(page);
    return new PageImpl<>(mongoTemplate.find(query, ExerciseModel.class), page,
        mongoTemplate.count(query, ExerciseModel.class));
  }
}
