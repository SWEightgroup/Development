package it.colletta.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.NotNull;

import org.springframework.stereotype.Service;

@Service
public class TeacherRepository {

    /**
     * Get all the sentences inserted by the teacher
     * 
     * @param teacherId Teacher id
     * @return Map contains token, uid and user information
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws Exception            Exception.
     */
    public String getAllSentences(@NotNull String teacherId)
            throws InterruptedException, ExecutionException {
        DocumentSnapshot document = 
        FirestoreClient.getFirestore().collection("users").document("teacherId").get().get();
        Map<String, Object> mapOfPhrases = new HashMap<String, Object>();
        String s = null;
        if(document.exists()) {
            Map<String, Object> mapOfObj = document.getData();
            s = (String) mapOfObj.get("exercises");
            
        }
        
        return s;
        //return mapOfPhrases;
    }

    /**
   * A teacher insert a new exercise
   * @param  phrase Teacher id, 
   * @return 
   * @throws 
   */
    public boolean insertPhrase(@NotNull String phrase, String uid){
        // va fatta una query con tutte gli esercizi scritti dall'insegnante, se non è presente
        // un esercizio con lo stesso testo che si vuole aggiungere allora aggiungo l'esercizio
        return true;
    }


}
















