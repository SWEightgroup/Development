package it.colletta.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import it.colletta.resources.RegistrationModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRepository {

  /**
   * get User Information from Firebase collection.
   * 
   * @param uid User id
   * @param token User token
   * @return Map contains token, uid and user information
   * @throws Exception Exception.
   */
  public Map<String, Object> getUserInformation(String uid, String token) throws Exception {
    ApiFuture<DocumentSnapshot> query =
        FirestoreClient.getFirestore().collection("users").document(uid).get();
    DocumentSnapshot document = query.get();

    if (document.exists()) {
      Map<String, Object> profile = document.getData();
      Map<String, Object> toReturn = new HashMap<String, Object>();
      toReturn.put("profile", profile);
      toReturn.put("uid", uid);
      toReturn.put("token", token);
      return toReturn;
    } else {
      throw new Exception();  
    }
  }

  /**
   * Add a user information in Users Collection.
   * 
   * @param newUser User information.
   * @param uid User uid (FirebaseAuth).
   * @return Map contains token and user's information.
   */
  public Map<String, Object> createNewUser(RegistrationModel newUser, String uid) {
    CollectionReference users = FirestoreClient.getFirestore().collection("users");
    List<ApiFuture<WriteResult>> futures = new ArrayList<>();
    Map<String, Object> userModel = newUser.gerUserModel();
    futures.add(users.document(uid).set(userModel));
    Map<String, Object> toReturn = new HashMap<String, Object>();
    toReturn.put("profile", userModel);
    toReturn.put("uid", uid);
    return toReturn;
  }

}
