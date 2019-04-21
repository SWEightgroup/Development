package it.colletta.service.teacher;

import it.colletta.model.TeacherModel;
import it.colletta.repository.user.TeacherRepository;
import it.colletta.security.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {

    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    private TeacherModel teacher;

    @Before
    public void setUp() throws Exception {

        teacher = TeacherModel.teacherBuilder()
                .id("1")
                .firstName("test")
                .lastName("teacher")
                .role(Role.TEACHER)
                .build();

        teacher.addTeacherExercise("333");
        teacher.addTeacherExercise("334");

        Mockito.when(teacherRepository.findById(teacher.getId())).thenReturn(Optional.of(teacher));
        Mockito.when(teacherRepository.save(any(TeacherModel.class))).then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    public void addTeacherExercise() {

        TeacherModel newTeacher = teacherService.addTeacherExercise(teacher.getId(), "333");

        assertTrue(newTeacher.getTeacherExercise().contains("333"));
    }

    @Test
    public void removeTeacherExercise() {

        TeacherModel newTeacher = teacherService.removeTeacherExercise(teacher.getId(), "333");

        assertFalse(newTeacher.getTeacherExercise().contains("333"));
        assertTrue(newTeacher.getTeacherExercise().contains("334"));
    }

    @Test
    public void getAllTeacherExercises() {

        List<String> teacherExercises = teacherService.getAllTeacherExercises(teacher.getId());

        assertEquals(teacherExercises.size(), 2);
    }
}