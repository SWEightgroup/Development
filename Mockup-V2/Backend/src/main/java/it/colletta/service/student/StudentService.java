package it.colletta.service.student;

import it.colletta.model.StudentModel;
import it.colletta.repository.user.StudentRepository;
import it.colletta.security.Role;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {

  @Autowired
  StudentRepository studentRepository;


  /**
   * Return all student user.
   *
   * @return list of students
   */
  public Iterable<StudentModel> getAllStudents() {
    return studentRepository.findAllByRole(Role.STUDENT);
  }


  /**
   * Delete an exercise.
   * <p>
   * TODO ritorna sempre optional vuoto..
   *
   * @param exerciseId Exercise id
   * @param userId User id
   * @return StudentModel
   */
  public Optional<StudentModel> deleteExerciseAssigned(final String exerciseId,
      final String userId) {
    Optional<StudentModel> user = studentRepository.findById(userId);
    if (user.isPresent()) {
      if (user.get().getRole().equals(Role.TEACHER)) {
        studentRepository.deleteFromExerciseToDo(exerciseId);
      }
    }
    return Optional.empty();
  }
}
