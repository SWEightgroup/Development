import { combineReducers } from 'redux';
import authReducer from './AuthReducer';
import ExerciseReducer from './ExerciseReducer';

const rootReducer = combineReducers({
  auth: authReducer,
  exercise: ExerciseReducer
});

export default rootReducer;

// the key name will be the data property on the state object
