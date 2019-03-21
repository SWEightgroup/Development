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

  /**
   * Get all the sentences inserted by the teacher
   *
   * @param teacherId Teacher id
   * @return Map contains token, uid and user information
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
    ArrayList<Object> phrasesList = (ArrayList<Object>) teacherDocSnapshot.getData().get("phrases");
    for (Object phrase : phrasesList) {
      Map<String, Object> solutionMap = new HashMap<>();
      String path = ((DocumentReference) phrase).getPath();
      String text =
          FirestoreClient.getFirestore().document(path).get().get().get("text").toString();
      List<QueryDocumentSnapshot> query =
          FirestoreClient.getFirestore()
              .document(path)
              .collection("solutions")
              .whereEqualTo("author", teacherId)
              .get()
              .get()
              .getDocuments();
      for (QueryDocumentSnapshot document : query) {
        solutionMap.put(document.getId(), document.get("text").toString());
      }
      phraseMap.put("frase", text);
      phraseMap.put("solutions", solutionMap);
    }
    return phraseMap;
  }

  /**
   * A teacher insert a new exercise
   *
   * @param phrase Teacher id, @return @throws
   */
  public boolean insertPhrase(@NotNull String phrase, String uid) {
    // va fatta una query con tutte gli esercizi scritti dall'insegnante, se non è
    // presente
    // un esercizio con lo stesso testo che si vuole aggiungere allora aggiungo
    // l'esercizio
    return true;
  }
}
