package it.colletta.repository.classes;

import it.colletta.model.ClassModel;

import java.util.List;

public interface ClassCustomQueryInterface {

  void renameClass(ClassModel classToUpdate);

  List<ClassModel> getAllTeacherClasses(String teacherId);
}
