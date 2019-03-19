package it.colletta.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.NotNull;
import com.google.firestore.v1beta1.Document;

import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository {

   /**
   * Get all the sentences inserted by the teacher 
   * 
   * @param  teacherId Teacher id
   * @return Map contains token, uid and user information
   * @throws Exception Exception.
   */
    public Map<String, Object> getAllSentences(@NotNull String teacherId) {
        ApiFuture<QuerySnapshot> exercisesQuery = 
            FirestoreClient.getFirestore().collection("exercises").whereArrayContains("author", teacherId).get();
        ApiFuture<QuerySnapshot> correctionsQuery = 
            FirestoreClient.getFirestore().collection("corrections").whereArrayContains("author", teacherId).get();
        List<QueryDocumentSnapshot> documents = exercisesQuery.get().getDocuments();
        for(QueryDocumentSnapshot document : documents)   {
            document.getClass(TeacherModel.class);
        }
        
        Map<String, Object> mapOfPhrases = new HashMap<String, Object>();

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
    }


}
















