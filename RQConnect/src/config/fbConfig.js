import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/auth";

// Replace this with your own config details
var config = {
  apiKey: "AIzaSyA5uvsfgSlPnaoacf1jIqWh_ArDO9dohsk",
  authDomain: "rqconnect.firebaseapp.com",
  databaseURL: "https://rqconnect.firebaseio.com",
  projectId: "rqconnect",
  storageBucket: "rqconnect.appspot.com",
  messagingSenderId: "182894304479"
};
firebase.initializeApp(config);
firebase.firestore().settings({ timestampsInSnapshots: true });

export default firebase;
