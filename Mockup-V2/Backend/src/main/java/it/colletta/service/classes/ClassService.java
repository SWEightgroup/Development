package it.colletta.service.classes;

import it.colletta.model.ClassModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.ClassHelper;
import it.colletta.model.helper.StudentClassHelper;
import it.colletta.model.helper.TeacherClasses;
import it.colletta.model.helper.UserLighterHelper;
import it.colletta.repository.classes.ClassRepository;
import it.colletta.service.user.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassService {

  private ClassRepository classRepository;
  private UserService userService;

  @Autowired
  public ClassService(ClassRepository classRepository, UserService userService) {
    this.classRepository = classRepository;
    this.userService = userService;
  }

  public String createNewClass(ClassHelper newClass, @NonNull String teacherId) {
    ClassModel classToAdd = ClassModel.builder()
            .name(newClass.getName())
            .teacherId(teacherId)
            .build();

    Optional<List<String>> studentsId = Optional.ofNullable(newClass.getStudentsId());
    studentsId.ifPresent(strings -> classToAdd.setStudentsId(strings
            .stream()
            .distinct()
            .collect(Collectors.toList())));
    classRepository.save(classToAdd);
    return classToAdd.getName();
  }


  public void modifyExistingStudentClass(StudentClassHelper studentClassHelper) throws Exception {
    classRepository.updateClass(studentClassHelper.getClassId(), studentClassHelper.getStudentsId(), studentClassHelper.getClassName());
  }

  public void deleteClass(@NonNull String classId) throws NullPointerException {
    classRepository.deleteById(classId);
  }

  public List<TeacherClasses> getAllClasses(@NonNull String teacherId) throws NullPointerException {
    List<ClassModel> classes = classRepository.getAllTeacherClasses(teacherId);
    List<TeacherClasses> allTeacherClasses = new ArrayList<>();

    for (ClassModel actualClassModel : classes) {
      List<UserModel> actualClassStudent =
              userService.getAllListUser(actualClassModel.getStudentsId());
      List<UserLighterHelper> actualClassStudentHelper = actualClassStudent.stream()
              .map(user -> UserLighterHelper.builder()
                      .id(user.getId())
                      .email(user.getUsername())
                      .firstName(user.getFirstName())
                      .lastName(user.getLastName())
                      .build())
              .collect(Collectors.toList());
      allTeacherClasses.add(
              TeacherClasses.builder()
                      .classId(actualClassModel.getId())
                      .className(actualClassModel.getName())
                      .students(actualClassStudentHelper)
                      .build());
    }
    return allTeacherClasses;
  }
}