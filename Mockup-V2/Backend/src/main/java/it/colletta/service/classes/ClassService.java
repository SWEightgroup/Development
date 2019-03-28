package it.colletta.service.classes;


import it.colletta.model.ClassModel;
import it.colletta.model.UserModel;
import it.colletta.repository.classes.ClassRepository;
import it.colletta.repository.exercise.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    ClassRepository classRepository;

    public Iterable<ClassModel> findAllClasses(List<String> classId){
        Iterable<String> list = classId;
        return classRepository.findAllById(list);
    }
}
