package it.colletta.service.student;

//import com.sun.javaws.progress.Progress;
import it.colletta.model.StudentModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.ProgressHelper;
import it.colletta.repository.user.StudentRepository;
import it.colletta.security.Role;
import it.colletta.service.user.UserService;
import it.colletta.strategy.NextObjectiveStrategyImpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class StudentService {

  private StudentRepository studentRepository;
  private UserService userService;

  /**
   * Construct a new student service using a repository
   * 
   * @param studentRepository the student repository
   */

  @Autowired
  public StudentService(StudentRepository studentRepository, UserService userService) {
    this.studentRepository = studentRepository;
    this.userService = userService;
  }

  /**
   * Return all student user.
   *
   * @return list of students
   */
  public Iterable<StudentModel> getAllStudents() {
    return studentRepository.findAllByRole(Role.STUDENT);
  }

  public Optional<StudentModel> findById(final String userId) {
    return studentRepository.findById(userId);
  }

  public void increaseLevel(Double mark, String studentId) {
    StudentModel student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    // mark is always >= 0
    if (mark >= 6.0) {
      student.setCurrentGoal(student.getCurrentGoal() + 1);
    } else {
      student.setCurrentGoal(Math.min(0, student.getCurrentGoal() - 1));
    }
    studentRepository.save(student);
  }

  public ProgressHelper getStudentProgress(String studentId) {
    StudentModel student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    NextObjectiveStrategyImpl strategy = new NextObjectiveStrategyImpl();
    double current = student.getCurrentGoal().doubleValue();
    Double nextProgress = strategy.nextProgress(current);
    return new ProgressHelper(current, nextProgress);
  }

  /**
   * Modify the List of the student favorite teacher List
   *
   * @param studentId the unique Id of the student
   */
  public void modifyFavoriteTeachers(String studentId, List<String> teacherId) {
    StudentModel student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    student.setFavoriteTeacherIds(teacherId);
    studentRepository.save(student);
  }

  /**
   * Return all the favorite student List of teacher.
   *
   * @param studentId the unique Id of the student
   * @return User actual List of favorite teacher.
   */
  public List<UserModel> getFavoriteTeachers(String studentId) {
    StudentModel student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    return userService.getAllListUser(student.getFavoriteTeacherIds());
  }

  /**
   * Add teacher of the student favorite teacher List
   *
   * @param studentId the unique Id of the student
   */
  public void addFavoriteTeacher(String studentId, String teacherId) throws ResourceNotFoundException {
    StudentModel student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    List<String> favoriteTeacherIds = student.getFavoriteTeacherIds();
    if (!favoriteTeacherIds.contains(teacherId)) {
      favoriteTeacherIds.add(teacherId);
    }
    studentRepository.save(student);
  }

  /**
   * Delete teacher of the student favorite teacher List
   *
   * @param studentId the unique Id of the student
   */
  public void deleteFavoriteTeacher(String studentId, String teacherId) throws ResourceNotFoundException {
    StudentModel student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    List<String> favoriteTeacherIds = student.getFavoriteTeacherIds();
    if (favoriteTeacherIds.contains(teacherId)) {
      favoriteTeacherIds.remove(teacherId);
    }
    studentRepository.save(student);
  }
}
