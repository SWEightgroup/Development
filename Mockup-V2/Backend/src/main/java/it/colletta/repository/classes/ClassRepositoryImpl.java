package it.colletta.repository.classes;

import it.colletta.model.ClassModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class ClassRepositoryImpl implements ClassCustomQueryInterface {

  MongoTemplate mongoTemplate;

  @Autowired
  /** @param mongoTemplate */
  public ClassRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void renameClass(ClassModel classToUpdate){
    Query query = new Query(Criteria.where("_id").is(classToUpdate.getId()));
    mongoTemplate.updateFirst(query, Update.update("name", classToUpdate.getName()), ClassModel.class);
  }

  @Override
  public List<ClassModel> getAllTeacherClasses(String teacherId){
      Query query = new Query();
      query.addCriteria(Criteria.where("teacherId").is(teacherId));
      //query.fields().exclude("studentsId");
      return mongoTemplate.find(query, ClassModel.class);
  }
}
