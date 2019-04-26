import { combineReducers } from 'redux';
import authReducer from './AuthReducer';
import ExerciseReducer from './ExerciseReducer';
import AdminReducer from './AdminReducer';
import ClassManagementReducer from './ClassManagementReducer';

const rootReducer = combineReducers({
  auth: authReducer,
  exercise: ExerciseReducer,
  admin: AdminReducer,
  class: ClassManagementReducer
});

export default rootReducer;

// the key name will be the data property on the state object
