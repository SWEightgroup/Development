// import axios from 'axios';
import { store } from '../index';

export const initializeNewExercise = () => {
  return dispatch => {
    dispatch({ type: 'INIT_EXERCISE' });
  };
};

export const updateNewExerciseState = newExercise => {
  store.dispatch({ type: 'UPDATE_EXERCISE', newExercise });
};

export const changeNewInputSentence = data => {
  return dispatch => {
    dispatch({ type: 'CHANGE_INPUT_SENTENCE_DATA', data });
  };
};
