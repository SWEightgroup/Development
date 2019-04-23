package it.colletta.service.student;

import it.colletta.model.ExerciseModel;
import it.colletta.model.StudentModel;
import it.colletta.repository.user.StudentRepository;
import it.colletta.security.Role;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class StudentService {

  @Autowired
  StudentRepository studentRepository;


  /**
   * adds the exercise to the todo list of students.
   *
   * @param assignedUsersIds List of users
   * @param exerciseModel Exericse
   */
  public void addExerciseItem(final List<String> assignedUsersIds,
      final ExerciseModel exerciseModel) {
    Iterable<StudentModel> students = studentRepository.findAllById(assignedUsersIds);
    for (StudentModel student : students) {
      if (student.getExercisesToDo() != null) {
        student.addExerciseToDo(exerciseModel.getId()); // TODO se un esercizio ritorna false lancio
      }
    }
    studentRepository.saveAll(students);
  }


  /**
   * Return all todo id of exercises.
   *
   * @param userId User id
   * @return list of exercise id
   */
  public List<String> getAllExerciseToDo(final String userId) {
    Optional<StudentModel> studentModel = studentRepository.findById(userId);
    if (studentModel.isPresent()) {
      return studentModel.get().getExercisesToDo();
    } else {
      throw new UsernameNotFoundException("User not found in the system");
    }
  }

  /**
   * Return all student user.
   *
   * @return list of students
   */
  public Iterable<StudentModel> getAllStudents() {
    return studentRepository.findAllByRole(Role.STUDENT);
  }


  /**
   * Shift an exercise from to do in done list. TODO FIXME: viene passato un intero ExerciseModel,
   * ma basta solo l'id dell'esercizio
   *
   * @param studentId Student Id
   * @param exerciseToCorrect Exercise
   */
  public void exerciseCompleted(final String studentId, final ExerciseModel exerciseToCorrect) {
    Optional<StudentModel> userOptional = studentRepository.findById(studentId);
    if (userOptional.isPresent()) {
      StudentModel user = userOptional.get();
      user.removeExerciseToDo(exerciseToCorrect.getId());
      user.addExerciseDone(exerciseToCorrect.getId());
      studentRepository.save(user);
    }
  }

  /**
   * Return all done exercise.
   *
   * @param userid Student id
   * @return List of exericses
   */
  public List<String> getAllExerciseDone(final String userid) {
    Optional<StudentModel> StudentModel = studentRepository.findById(userid);
    if (StudentModel.isPresent()) {
      return StudentModel.get().getExercisesDone();
    } else {
      throw new UsernameNotFoundException("User not found in the system");
    }
  }

  /**
   * Return all done exercise.
   * <p>
   * TODO FIXME: metodo duplicato di getAllExerciseToDo
   *
   * @param userid Student id
   * @return List of exericses
   */
  public List<String> getAllToDoByAuthorId(final String userid) {
    Optional<StudentModel> StudentModel = studentRepository.findById(userid);
    if (StudentModel.isPresent()) {
      return StudentModel.get().getExercisesToDo();
    } else {
      throw new UsernameNotFoundException("User not found in the system");
    }
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

  public List<ObjectId> getAllExercises(String userId) {
    StudentModel student = studentRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    List<String> exercises = student.getExercisesDone();
    exercises.addAll(student.getExercisesToDo());
    return exercises.stream().map(ObjectId::new).collect(Collectors.toList());
  }
}
