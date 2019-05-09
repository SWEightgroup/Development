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
  public ClassRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void renameClass(String classId, String newClassName) {
    Query query = new Query(Criteria.where("_id").is(classId));
    mongoTemplate.updateFirst(query, Update.update("name", newClassName), ClassModel.class);
  }


  @Override
  public List<ClassModel> getAllTeacherClasses(String teacherId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("teacherId").is(teacherId));
    return mongoTemplate.find(query, ClassModel.class);
  }

  @Override
  public void updateClass(String classId, List<String> studentId, String className) {
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(classId));
    mongoTemplate.updateFirst(query, Update.update("studentsId", studentId), ClassModel.class);
    mongoTemplate.updateFirst(query, Update.update("name", className), ClassModel.class);

  }
}
