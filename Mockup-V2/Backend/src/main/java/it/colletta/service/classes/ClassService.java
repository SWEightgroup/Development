package it.colletta.service.classes;

import it.colletta.model.ClassModel;
import it.colletta.model.StudentModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.ClassHelper;
import it.colletta.model.helper.StudentClassHelper;
import it.colletta.repository.classes.ClassRepository;
import it.colletta.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
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

  public String createNewClass(ClassHelper newClass, @NonNull String teacherId){
    ClassModel classToAdd = ClassModel.builder()
            .name(newClass.getName())
            .teacher(newClass.getTeacher())
            .teacherId(teacherId)
            .build();

    Optional<List<String>> studentsId = Optional.ofNullable(newClass.getStudentsId());
    if(studentsId.isPresent()){

      classToAdd.setStudentsId(studentsId.get()
              .stream()
              .distinct()
              .collect(Collectors.toList()));
    }
    classRepository.save(classToAdd);
    return classToAdd.getName();
  }

  public String renameExistingClass(@NotNull String classId, String newClassName) throws Exception{
    classRepository.renameClass(classId, newClassName);
    return newClassName;

  }

  public void modifyExistingStudentClass(StudentClassHelper studentClassHelper) throws Exception{
    classRepository.updateStudentList(studentClassHelper.getClassId(), studentClassHelper.getStudentsId());
  }

  public void deleteClass(@NotNull String classId) throws Exception{
    classRepository.deleteById(classId);
  }

  public List<ClassModel> getAllClasses(@NotNull String teacherId) throws Exception {
    return classRepository.getAllTeacherClasses(teacherId);
  }
}