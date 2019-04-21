package it.colletta.service.teacher;

import it.colletta.model.TeacherModel;
import it.colletta.repository.user.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;


    public TeacherModel addTeacherExercise(String teacherId, String exerciseId) {
        Optional<TeacherModel> teacherModel = teacherRepository.findById(teacherId);
        if (teacherModel.isPresent()) {
            TeacherModel teacher = teacherModel.get();
            teacher.addTeacherExercise(exerciseId);
            return teacherRepository.save(teacher);

        } else {
            throw new UsernameNotFoundException("User not found in the system");
        }
    }

    public TeacherModel removeTeacherExercise(String teacherId, String exerciseId) {
        Optional<TeacherModel> teacherModel = teacherRepository.findById(teacherId);
        if (teacherModel.isPresent()) {
            TeacherModel teacher = teacherModel.get();
            teacher.removeTeacherExercise(exerciseId);
            return teacherRepository.save(teacher);

        } else {
            throw new UsernameNotFoundException("User not found in the system");
        }
    }

    public List<String> getAllTeacherExercises(String teacherId) {
        Optional<TeacherModel> teacherModel = teacherRepository.findById(teacherId);
        if (teacherModel.isPresent()) {
            return teacherModel.get().getTeacherExercise();

        } else {
            throw new UsernameNotFoundException("User not found in the system");
        }
    }
}