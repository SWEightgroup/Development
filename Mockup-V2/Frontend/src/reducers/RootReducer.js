import { combineReducers } from 'redux';
import authReducer from './AuthReducer';
import ExerciseReducer from './ExerciseReducer';
import AdminReducer from './AdminReducer';

const rootReducer = combineReducers({
  auth: authReducer,
  exercise: ExerciseReducer,
  admin: AdminReducer
});

export default rootReducer;

// the key name will be the data property on the state object
