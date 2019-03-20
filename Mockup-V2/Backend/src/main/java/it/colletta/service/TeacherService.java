package it.colletta.service;

import it.colletta.repository.TeacherRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

  @Autowired private TeacherRepository teacherRepository;
  @Autowired private FirebaseAuthImplementation auth;

  public Map<String, Object> getInsertedSenteces(String teacherId) {
    String s = teacherRepository.getAllSentences(teacherId);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("path", s);
    return map;
  }
}
