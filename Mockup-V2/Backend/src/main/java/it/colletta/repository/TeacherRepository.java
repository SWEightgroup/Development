package it.colletta.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.NotNull;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
  public Object getAllSentences(@NotNull String teacherId) throws InterruptedException, ExecutionException {
    ApiFuture<DocumentSnapshot> documentSnapshot = FirestoreClient.getFirestore().collection("users")
        .document(teacherId).get();
    DocumentSnapshot teacherDocSnapshot = documentSnapshot.get();
    Map<String, Object> map = new HashMap<>();
    Map<String, Object> temporanyMap = null;
    @SuppressWarnings("unchecked")
    ArrayList<Object> listExercises = (ArrayList<Object>) teacherDocSnapshot.getData().get("exercises");
    for (Object exercise : listExercises) {
      // DocumentSnapshot solutionDocument = FirestoreClient.getFirestore()
      // .document(((DocumentReference) exercise).getPath()).get().get();
      // temporanyMap = solutionDocument.getData();
      // map.put("testo", temporanyMap.get("phrase").toString());
      // map.put("soluzione", temporanyMap.get("solution").toString());
      String path = ((DocumentReference) exercise).getPath();
      String text = FirestoreClient.getFirestore().document(path).get().get().get("phrase").toString();
      map.put("dio", text);
    }
    return map;
    /*
     * document.Map<String, Object> mapOfPhrases = new HashMap<String, Object>(); //
     * TODO String s = null; if (document.exists()) {
     * 
     * @SuppressWarnings("unchecked") ArrayList<Object> rf = (ArrayList<Object>)
     * document.getData().get("exercises");
     * 
     * for (Object item : rf) {
     * FirestoreClient.getFirestore().document(((DocumentReference)
     * item).getPath()).get().get().get(""); }
     * 
     * return ((DocumentReference) rf.get(0)).getPath(); // return rf.getPath(); }
     * return null; // return mapOfPhrases;
     */
  }

  /**
   * A teacher insert a new exercise
   *
   * @param phrase Teacher id, @return @throws
 * @throws ExecutionException 
 * @throws InterruptedException 
   */
  public void insertPhrase(@NotNull String phrase, String uid,Object soluzione) throws InterruptedException, ExecutionException {
    // va fatta una query con tutte gli esercizi scritti dall'insegnante, se non è
    // presente
    // un esercizio con lo stesso testo che si vuole aggiungere allora aggiungo
    // l'esercizio
	  	HashMap<String,Object> elementoDaInserire = new HashMap<String,Object>();
	  	elementoDaInserire.put("testo", phrase);
	  	elementoDaInserire.put("autore", uid);
	  	
	  	ApiFuture<DocumentReference> fraseInserita = FirestoreClient.getFirestore().collection("frasi").add(elementoDaInserire);
	  	
	    ///aggiungo alla collection degli esercizi
	    ApiFuture<DocumentReference> esercizioInserito= FirestoreClient.getFirestore().collection("esercizi").add(elementoDaInserire);
	    
	    HashMap<String,Object> soluzioneDaInserire = new HashMap<String,Object>();
	    
	    soluzioneDaInserire.put("autore", uid);
	    soluzioneDaInserire.put("soluzione", soluzione);
	    
	    ApiFuture<DocumentReference> soluzioneInserita= 
	    		FirestoreClient.getFirestore()
	    		.collection("esercizi")
	    		.document(esercizioInserito.get().getId())
	    		.collection("soluzioni").add(soluzioneDaInserire);
	    
	    
	 // Atomically add a new region to the "regions" array field.
	    ApiFuture<WriteResult> arrayUnion = fraseInserita.get().update("soluzioni",FieldValue.arrayUnion(soluzioneInserita.get()));


  }
}