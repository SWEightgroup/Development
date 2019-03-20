package it.colletta.repository;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
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
   * @throws Exception Exception.
   */
  public Object getAllSentences(@NotNull String teacherId)
      throws InterruptedException, ExecutionException {
    DocumentSnapshot document =
        FirestoreClient.getFirestore().collection("users").document(teacherId).get().get();
    Map<String, Object> mapOfPhrases = new HashMap<String, Object>();  //	TODO 
    String s = null;
    if (document.exists()) {
      
    @SuppressWarnings("unchecked")
	ArrayList<Object> rf = (ArrayList<Object>) document.getData().get("exercises");
    
    for(Object item : rf) {
    	FirestoreClient.getFirestore().document(((DocumentReference)item).getPath()).get().get().get("");
    }
    		
    return ((DocumentReference)rf.get(0)).getPath();
      //return rf.getPath();
    }
    return null;
    // return mapOfPhrases;
  }

  /**
   * A teacher insert a new exercise
   *
   * @param phrase Teacher id,
   * @return
   * @throws
   */
  public boolean insertPhrase(@NotNull String phrase, String uid) {
    // va fatta una query con tutte gli esercizi scritti dall'insegnante, se non è presente
    // un esercizio con lo stesso testo che si vuole aggiungere allora aggiungo l'esercizio
    return true;
  }
}
