package it.colletta.test.repository;

import it.colletta.repository.TeacherRepository;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class TeacherRepositoryTest {

    @Test
    public void getAllSentences() {
        TeacherRepository teacherRepository = new TeacherRepository();
        try {
            Map<String, Object> objectMap;
            objectMap = teacherRepository.getAllSentences("81jYxnKvPFcFxhLgkxqxGSh39OA3");
            System.out.print(objectMap);
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}