package it.colletta.service;

import it.colletta.repository.TeacherRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class TeacherService {

  @Autowired
  private TeacherRepository teacherRepository;

  public Map<String, Object> getInsertedSenteces(String teacherId) {
    Map<String, Object> mapOfSetence = null;
    try {
      mapOfSetence = teacherRepository.getAllSentences(teacherId);
      return mapOfSetence;
    } catch (InterruptedException | ExecutionException e) {
    }
    return mapOfSetence;
  }
}
