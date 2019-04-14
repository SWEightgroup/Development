package it.colletta.repository.exercise;

import com.mongodb.client.result.UpdateResult;
import it.colletta.model.ExerciseModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class ExerciseRepositoryImpl implements ExerciseCustomQueryInterface {

  private MongoTemplate mongoTemplate;

  /** @param mongoTemplate */
  @Autowired
  public ExerciseRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public UpdateResult modifyAuthorName(String newAuthorName, String authorId) {
    Query exerciseToUpdate = new Query(Criteria.where("authorId").is(authorId));
    Update modify = new Update();
    modify.set("authorName", newAuthorName);
    return mongoTemplate.updateMulti(exerciseToUpdate, modify, ExerciseModel.class);
  }

  @Override
  public Page<ExerciseModel> findAllByPaged(Pageable page, Iterable<String> ids) {
    Query query = new Query(Criteria.where("_id").in(ids)).with(page);
    List<ExerciseModel> exerciseModelList = mongoTemplate.find(query, ExerciseModel.class);
    long total = mongoTemplate.count(query, ExerciseModel.class);
    Page<ExerciseModel> newPage = new PageImpl<ExerciseModel>(exerciseModelList, page, total);
    return newPage;
  }
}
