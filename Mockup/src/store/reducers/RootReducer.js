import { combineReducers } from 'redux';
import { firestoreReducer } from 'redux-firestore';
import { firebaseReducer } from 'react-redux-firebase';
import authReducer from './AuthReducer';
import ExerciseReducer from './ExerciseReducer';

const rootReducer = combineReducers({
  auth: authReducer,
  issue: ExerciseReducer,
  firestore: firestoreReducer,
  firebase: firebaseReducer
});

export default rootReducer;

// the key name will be the data property on the state object
