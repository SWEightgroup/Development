package it.colletta.repository.classes;

import it.colletta.model.ClassModel;
import java.util.List;

public interface ClassCustomQueryInterface {

  void renameClass(String classId, String newClassName);

  List<ClassModel> getAllTeacherClasses(String teacherId);

  void updateClass(String classId, List<String> studentId, String className);

}
