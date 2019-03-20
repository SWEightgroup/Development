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
    Map<String, Object> map = new HashMap<String, Object>();
    try {
    Object s = teacherRepository.getAllSentences(teacherId);
    map.put("path", s);
    }
    catch(InterruptedException | ExecutionException e) {
      
    }
    return map;
  }
}
