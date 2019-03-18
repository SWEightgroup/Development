package service;

import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class FirebaseAuthImplementation implements FirebaseAuthInterface {

  private static final String loginUrl =
      "https://www.googleapis.com/identitytoolkit/v3/relyingparty/verifyPassword?key=AIzaSyAkZ6klWWfD_GLI9JGxh8pgd8qCR2x1-9g";
  private static final String keyPath = "src/key.json";

  public FirebaseAuthImplementation() {
    try {

      FirebaseApp.initializeApp(
          FirebaseOptions.builder()
              .setCredentials(
                  GoogleCredentials.fromStream(
                      new FileInputStream(FirebaseAuthImplementation.keyPath)))
              .setFirestoreOptions(
                  FirestoreOptions.newBuilder().setTimestampsInSnapshotsEnabled(true).build())
              .build());
    } catch (Exception e) {
      e.printStackTrace();
    }
    /*db = FirestoreClient.getFirestore();
    firebaseAuth = FirebaseAuth.getInstance();*/
  }

  @Override
  public String createUser(String email, String password) throws FirebaseAuthException {

    CreateRequest request = new CreateRequest();
    request.setEmail(email);
    request.setPassword(password);
    return FirebaseAuth.getInstance().createUser(request).getUid();
  }

  @Override
  public String getToken(String username, String password) throws Exception {
    HttpURLConnection urlRequest = null;

    try {
      String token = null;
      URL url = new URL(FirebaseAuthImplementation.loginUrl);
      urlRequest = (HttpURLConnection) url.openConnection();
      urlRequest.setDoOutput(true);
      urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
      OutputStream os = urlRequest.getOutputStream();
      OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
      osw.write(
          "{\"email\":\""
              + username
              + "\",\"password\":\""
              + password
              + "\",\"returnSecureToken\":true}");
      osw.flush();
      osw.close();

      urlRequest.connect();

      JsonParser jp = new JsonParser(); // from gson
      JsonElement root =
          jp.parse(
              new InputStreamReader(
                  (InputStream)
                      urlRequest.getContent())); // Convert the input stream to a json element
      JsonObject rootobj = root.getAsJsonObject(); // May be an array, may be an object.

      token = rootobj.get("idToken").getAsString();
      return token;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (urlRequest != null) urlRequest.disconnect();
    }
  }

  @Override
  public void deleteUser(String token, String uid) {
    // TODO Auto-generated method stub

  }

  @Override
  public Map<String, Object> updateUser(String token, Map<String, Object> user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getUid(String token) throws FirebaseAuthException {
    return FirebaseAuth.getInstance().verifyIdToken(token).getUid();
  }

  @Override
  public String createToken(String uid) throws FirebaseAuthException {
    return FirebaseAuth.getInstance().createCustomToken(uid);
  }
}
