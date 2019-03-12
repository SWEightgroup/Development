package library;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FireBaseAuth {

    private static final String BASE_URL = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/";
    private static final String OPERATION_AUTH = "verifyPassword";
    private static final String OPERATION_ACCOUNT_INFO = "getAccountInfo";
    private static final String OPERATION_NEW_USER = "signupNewUser";
    

    private String firebaseKey;

    private static FireBaseAuth instance = null;

    protected FireBaseAuth() {
       firebaseKey = "AIzaSyAkZ6klWWfD_GLI9JGxh8pgd8qCR2x1-9g";
    }

    public static FireBaseAuth getInstance() {
      if(instance == null) {
         instance = new FireBaseAuth();
      }
      return instance;
    }

    /**
     * authenticates the user and return a token
     * 
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public String auth(String username, String password) throws Exception { 

        HttpURLConnection urlRequest = null;
        String token = null;

        try {
            URL url = new URL(BASE_URL+OPERATION_AUTH+"?key="+firebaseKey);
            urlRequest = (HttpURLConnection) url.openConnection();
            urlRequest.setDoOutput(true);
            urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = urlRequest.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write("{\"email\":\""+username+"\",\"password\":\""+password+"\",\"returnSecureToken\":true}");
            osw.flush();
            osw.close();

            urlRequest.connect();

            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) urlRequest.getContent())); //Convert the input stream to a json element
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 

            token = rootobj.get("idToken").getAsString();

        } catch (Exception e) {
        	e.printStackTrace();
            throw e;
        } finally {
            urlRequest.disconnect();
        }

        return token;
    }
    
    
    /**
     * Create a new user, adding personal data to the collection users.
     * 
     * @param username
     * @param password
     * @return token
     * @throws Exception
     */
    public String newUser(String username, String password) throws Exception { 

        HttpURLConnection urlRequest = null;
        String token = null;

        try {
            URL url = new URL(BASE_URL+OPERATION_NEW_USER+"?key="+firebaseKey);
            urlRequest = (HttpURLConnection) url.openConnection();
            urlRequest.setDoOutput(true);
            urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = urlRequest.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write("{\"email\":\""+username+"\",\"password\":\""+password+"\",\"returnSecureToken\":true}");
            osw.flush();
            osw.close();

            urlRequest.connect();

            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) urlRequest.getContent())); //Convert the input stream to a json element
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 

            token = rootobj.get("idToken").getAsString();

        } catch (Exception e) {
        	e.printStackTrace();
            throw e;
        } finally {
            urlRequest.disconnect();
        }

        return token;
    }

    
    /**
     * Return the account information
     * 
     * @param token
     * @return account information 
     * @throws Exception
     */
    public String getAccountInfo(String token) throws Exception {

        HttpURLConnection urlRequest = null;
        String uid = null;
        
        try {
            URL url = new URL(BASE_URL+OPERATION_ACCOUNT_INFO+"?key="+firebaseKey);
            urlRequest = (HttpURLConnection) url.openConnection();
            urlRequest.setDoOutput(true);
            urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = urlRequest.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write("{\"idToken\":\""+token+"\"}");
            osw.flush();
            osw.close();
            urlRequest.connect();

            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) urlRequest.getContent())); //Convert the input stream to a json element
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 

            uid = rootobj.get("users").getAsJsonArray().get(0).getAsJsonObject().get("localId").getAsString();
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        } finally {
            urlRequest.disconnect();
        }

        return uid;

    }

}