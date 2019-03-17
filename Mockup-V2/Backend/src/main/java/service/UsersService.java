package service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import resources.RegistrationModel;

@Service
public class UsersService {

  /**
   * @param login
   * @param auth
   * @return
   * @throws Exception
   */
  public static Map<String, Object> login(String email, String password, FirebaseAuthInterface auth)
      throws Exception {

    String token = auth.getToken(email, password);
    String uid = auth.getUid(token);
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
   * @param newUser
   * @param auth
   * @return
   * @throws Exception
   */
  public static Map<String, Object> newUser(RegistrationModel newUser, FirebaseAuthInterface auth)
      throws Exception {
    try {
      String uid = auth.createUser(newUser.getEmail(), newUser.getPassword());
      CollectionReference users = FirestoreClient.getFirestore().collection("users");
      List<ApiFuture<WriteResult>> futures = new ArrayList<>();
      Map<String, Object> userModel = newUser.gerUserModel();
      futures.add(users.document(uid).set(userModel));
      Map<String, Object> toReturn = new HashMap<String, Object>();
      toReturn.put("profile", userModel);
      toReturn.put("token", auth.createToken(uid));
      toReturn.put("uid", uid);
      return toReturn;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}
