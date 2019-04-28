package it.colletta.service;

import static org.junit.Assert.assertEquals;

import it.colletta.model.ClassModel;
import it.colletta.model.helper.ClassHelper;
import it.colletta.repository.classes.ClassRepository;
import it.colletta.service.classes.ClassService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ClassServiceTest {

    @Mock
    private ClassRepository classRepository;

    @InjectMocks
    private ClassService classService;

    private ClassModel classModel;

    private ClassHelper classHelper;


    @Before
    public void setUp() {

        List<String> studentsId = new ArrayList<>();
        studentsId.add("104");

        classModel = classModel.builder()
                .id("1")
                .name("nomeclasse")
                .studentsId(studentsId)
                .teacherId("100")
                .build();

        classHelper = classHelper.builder()
                .classId("1")
                .name("nomeclasse")
                .studentsId(studentsId)
                .teacherId("100")
                .build();

    }

    //public String createNewClass(ClassHelper newClass, @NonNull String teacherId)

    @Test
    public void createNewClass(){
        Mockito.when( classRepository.save(any(ClassModel.class))).thenReturn(classModel);

        String myClass = classService.createNewClass(classHelper,classHelper.getTeacherId());

        assertEquals(myClass, "nomeclasse");

    }



}
