package it.colletta.service.classes;

import it.colletta.model.ClassModel;
import it.colletta.model.StudentModel;
import it.colletta.model.UserModel;
import it.colletta.repository.classes.ClassRepository;
import it.colletta.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private UserService userService;


    public String createNewClass(ClassModel classToAdd, String teacherId){
        classToAdd.setTeacherId(teacherId);
        classRepository.save(classToAdd);
        return classToAdd.getName();
    }

    public StudentModel addNewStudent(String studentId, String classId) throws Exception {
        Optional<ClassModel> classById =  classRepository.findById(classId);
        Optional<UserModel> studentToAdd = userService.findById(studentId);
        if(classById.isPresent() &&  studentToAdd.isPresent()){
            ClassModel myClass = classById.get();
            myClass.getStudentsId().add(studentId);
            return (StudentModel) studentToAdd.get();
        }
        else{
            throw new Exception();
        }
    }

    public void removeExistingStudent(String studentId, String classId) throws Exception{
        Optional<ClassModel> classById =  classRepository.findById(classId);
        if(classById.isPresent()){
            List<String> studentsClassId = classById.get().getStudentsId();
            studentsClassId.remove(studentId);
        }
        else{
            throw new Exception();
        }

    }

    public String renameExistingClass(ClassModel renamedClassModel) throws Exception{
        if(classRepository.findById(renamedClassModel.getId()).isPresent()) {
            classRepository.renameClass(renamedClassModel);
            return renamedClassModel.getName();
        }
        else{
            throw new Exception();
        }
    }

    public void deleteExistingClass(String classId) throws Exception{
        if(classRepository.findById(classId).isPresent()){
            classRepository.deleteById(classId);
        }
        else{
            throw new Exception();
        }
    }

    public List<ClassModel> getAllClasses(String teacherId) throws Exception{
        if(userService.findById(teacherId).isPresent()){
            return classRepository.getAllTeacherClasses(teacherId);
        }
        else{
            throw new Exception();
        }
    }
}