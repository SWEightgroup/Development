package it.colletta.service.student;

import it.colletta.model.StudentModel;
import it.colletta.repository.user.StudentRepository;
import it.colletta.security.Role;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {

  private StudentRepository studentRepository;

  /**
   * Construct a new student service using a repository
   * @param studentRepository the student repository
   */
  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }
  /**
   * Return all student user.
   *
   * @return list of students
   */
  public Iterable<StudentModel> getAllStudents() {
    return studentRepository.findAllByRole(Role.STUDENT);
  }
}
