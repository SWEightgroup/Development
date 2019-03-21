package it.colletta.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository {


  final int MAXIMUM_SOLUTION = 2;
  /**
   * Get all the sentences inserted by the teacher
   *
   * @param teacherId Teacher id
   * @return Map 
   * @throws ExecutionException
   * @throws InterruptedException
   * @throws Exception Exception.
   */
  public Map<String, Object> getAllSentences(@NotNull String teacherId)
      throws InterruptedException, ExecutionException {
    ApiFuture<DocumentSnapshot> documentSnapshot =
        FirestoreClient.getFirestore().collection("users").document(teacherId).get();
    DocumentSnapshot teacherDocSnapshot = documentSnapshot.get();
    Map<String, Object> phraseMap = new HashMap<>();
    @SuppressWarnings("unchecked")
    ArrayList<Object> phrasesPathList = (ArrayList<Object>) (teacherDocSnapshot.getData()).get("phrases");    // phrases path of the teacher 
    for (Object phrase : phrasesPathList) {
      Map<String, Object> solutionMap = new HashMap<>(MAXIMUM_SOLUTION); 
      String pathPhrase = ((DocumentReference) phrase).getPath();           // the phrase position inside the table "phrases"
      String text =
          FirestoreClient.getFirestore().document(pathPhrase).get().get().get("text").toString();   // the text of the phrase 
      List<QueryDocumentSnapshot> query =
          FirestoreClient.getFirestore()
              .document(pathPhrase)
              .collection("solutions")
              .whereEqualTo("author", teacherId)
              .get()
              .get()
              .getDocuments();
      for (QueryDocumentSnapshot document : query) {
        solutionMap.put(document.getId(), document.get("text").toString());
      }
     // phraseMap.put("phrase", text);        //the text of the phrase
      phraseMap.put(text, solutionMap);    // the sub-map of the phrase solutions
    }
    return phraseMap;
  }

  /**
   * A teacher insert a new exercise
   *
   * @param phrase the text of the phrase
   * @param solution the solution of the phrase 
   * @param teacherId the document identifier of the collection in firebase
   * @throws something 
   */

  public boolean insertPhrase(@NotNull String teacherId, @NotNull String phrase,
    @NotNull String solution, @NotNull String freeLingSolution, String alternativeSolution) {
    // controllare se la frase è già presente 
    // se non lo è: 
    List<QueryDocumentSnapshot> phraseWithSolutions = 
        FirestoreClient.getFirestore().collection("phrases").whereEqualTo("text", phrase).get().get().getDocuments(); // check if the phrases already exist 
    if(phraseWithSolutions.size() == 1) {   //the phrase already exsist so I have to add only the solutions 
      DocumentReference documentref = phraseWithSolutions.get(0).getReference();
      Map<String, Object> iMap = new HashMap<String, Object>(3);
      Map<String, Object>[] submap = new HashMap<String, Object>(3);
      iMap.put("author", teacherId);
      
      FirestoreClient.getFirestore().document(
        documentref.getPath()
        ).collection("solutions").add("");
    }
    Map<String, Object> update = new HashMap<>();  
    ApiFuture<WriteResult> writeResult = 
        FirestoreClient.getFirestore().collection("users").document(teacherId).get();        
    DocumentSnapshot teacherDocSnapshot = writeResult.get();

    

    return true;
  }
}
