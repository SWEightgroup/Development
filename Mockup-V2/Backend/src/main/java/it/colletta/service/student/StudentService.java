package it.colletta.service.student;

import it.colletta.model.StudentModel;
import it.colletta.repository.user.StudentRepository;
import it.colletta.security.Role;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

  public void increaseLevel(Double mark, String studentId) {
    StudentModel student = studentRepository.findById(studentId).orElseThrow(() ->
        new ResourceNotFoundException("Student not found"));
    //mark is always >= 0
    if (mark >= 6.0) {
      student.setCurrentGoal(student.getCurrentGoal() + 1);
    }
    else {
      student.setCurrentGoal(student.getCurrentGoal() - 1);
    }
    studentRepository.save(student);
  }
}
