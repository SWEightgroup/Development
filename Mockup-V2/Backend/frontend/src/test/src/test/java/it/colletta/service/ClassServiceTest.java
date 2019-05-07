package it.colletta.service;

import static org.junit.Assert.assertEquals;

import it.colletta.model.ClassModel;
import it.colletta.model.UserModel;
import it.colletta.model.helper.ClassHelper;
import it.colletta.model.helper.StudentClassHelper;
import it.colletta.repository.classes.ClassRepository;
import it.colletta.security.Role;
import it.colletta.service.classes.ClassService;
import it.colletta.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class ClassServiceTest {

    @Mock
    private ClassRepository classRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ClassService classService;

    private ClassModel classModel;

    private ClassHelper classHelper;

    private UserModel userModel;

    private StudentClassHelper studentClassHelper;


    @Before
    public void setUp() {

        List<String> studentsId = new ArrayList<>();
        studentsId.add("104");

        Date date = new Date(2323223232L);

        classModel = classModel.builder()
                .id("1")
                .name("nomeclasse")
                .studentsId(studentsId)
                .teacherId("100")
                .build();

        userModel = UserModel.builder()
                .id("123")
                .firstName("firstname")
                .email("email@email.com")
                .enabled(true)
                .role(Role.TEACHER)
                .lastName("lastname")
                .dateOfBirth(date)
                .language("it")
                .build();


        classHelper = classHelper.builder()
                .classId("1")
                .name("nomeclasse")
                .studentsId(studentsId)
                .teacherId("100")
                .build();

        studentClassHelper = studentClassHelper.builder()
                .classId("0")
                .className("classe0")
                .studentsId(studentsId)
                .build();

    }

    @Test
    public void createNewClass(){
        Mockito.when( classRepository.save(any(ClassModel.class))).thenReturn(classModel);

        String myClass = classService.createNewClass(classHelper,classHelper.getTeacherId());

        assertEquals(myClass, "nomeclasse");

    }

    @Test
    public void modifyExistingStudentClass() throws Exception {

        classService.modifyExistingStudentClass(studentClassHelper);

        Mockito.verify(classRepository).updateClass(
                studentClassHelper.getClassId(),
                studentClassHelper.getStudentsId(),
                studentClassHelper.getClassName()
        );
    }

    @Test
    public void deleteClass(){
        classService.deleteClass(anyString());
        Mockito.verify(classRepository).deleteById(anyString());
    }



    @Test
    public void getAllClasses(){

        List<ClassModel> listclassmodel = new ArrayList<>();
        listclassmodel.add(classModel);

        List<String> studentsId = new ArrayList<>();
        studentsId.add("104");

        List<UserModel> listUsermodel = new ArrayList<>();
        listUsermodel.add(userModel);

        /*List<TeacherClasses> teacherClasseslist = new ArrayList<>();

        Mockito.when(classRepository.getAllTeacherClasses(anyString())).thenReturn(listclassmodel);
        Mockito.when(userService.getAllListUser(studentsId)).thenReturn(listUsermodel);

        teacherClasseslist = classService.getAllClasses(anyString());

        assertEquals(teacherClasseslist.get(0).getClassName(), "nomeclasse"); */


    }


}
