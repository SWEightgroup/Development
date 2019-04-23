package it.colletta.service.classes;

import it.colletta.model.ClassModel;
import it.colletta.model.StudentModel;
import it.colletta.repository.classes.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    private ClassRepository classRepository;

    @Autowired
    ClassService(ClassRepository classRepository){
      this.classRepository = classRepository;
    }

    public String createNewClass(ClassModel classToAdd){
         return new String();
    }

    public StudentModel addNewStudent(String studentId){
        return new StudentModel();
    }

    public StudentModel removeExistingStudent(String studentId){
        return new StudentModel();
    }

    public String renameExistingClass(ClassModel renamedClassModel){
        return new String();
    }

    public void deleteExistingClass(String classId){
    }

}
