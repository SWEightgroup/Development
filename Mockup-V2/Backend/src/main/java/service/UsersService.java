package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import library.FireBaseAuth;
import resources.UserModel;

@Service
public class UsersService {
	private static FireBaseAuth auth = FireBaseAuth.getInstance();
	private FileInputStream serviceAccount;
	private GoogleCredentials credentials;
	private Firestore db;

	public UsersService() throws IOException{
		serviceAccount = 	new FileInputStream("src/key.json");
		credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(credentials)
				.build();
		FirebaseApp.initializeApp(options);

		db = FirestoreClient.getFirestore();		
	}

	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public String getToken(String email, String password) throws Exception {
		return auth.auth(email, password);
	}

	/**
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> login(String token) throws Exception {

		String uid = auth.getAccountInfo(token);
		ApiFuture<DocumentSnapshot> query = db.collection("users").document(uid).get();
		DocumentSnapshot document = query.get();

		if (document.exists()) {
			Map<String, Object> profile= document.getData();
			profile.put("uid", uid);
			return profile;
		} else {
			throw new Exception();
		}

	}

	public String newUser(UserModel newUser) throws Exception {
		String token = auth.newUser(newUser.email, newUser.password);
		String uid = auth.getAccountInfo(token);
		
		CollectionReference cities = db.collection("users");
		List<ApiFuture<WriteResult>> futures = new ArrayList<>();
		futures.add(cities.document(uid).set(newUser));
		return token;
	}



}
