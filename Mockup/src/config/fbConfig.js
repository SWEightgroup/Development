import firebase from 'firebase/app';
import 'firebase/firestore';
import 'firebase/auth';

// Replace this with your own config details
const config = {
  apiKey: 'AIzaSyAkZ6klWWfD_GLI9JGxh8pgd8qCR2x1-9g',
  authDomain: 'colletta-9fb53.firebaseapp.com',
  databaseURL: 'https://colletta-9fb53.firebaseio.com',
  projectId: 'colletta-9fb53',
  storageBucket: 'colletta-9fb53.appspot.com',
  messagingSenderId: '797571105871'
};
firebase.initializeApp(config);
firebase.firestore().settings({ timestampsInSnapshots: true });

export default firebase;
