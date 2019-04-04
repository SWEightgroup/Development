// import axios from 'axios';
import axios from 'axios';
import { store } from '../index';

export const initializeNewExercise = () => {
  return dispatch => {
    dispatch({ type: 'INIT_EXERCISE' });
  };
};

export const initNewExerciseState = newExercise => {
  const allowedPunctuation = /[a-zA-Z]/g;
  return dispatch => {
    dispatch({
      type: 'INIT_NEW_EXERCISE',
      newExercise: {
        ...newExercise,
        userSolution: newExercise.sentence
          .filter(word => word.match(allowedPunctuation))
          .map(() => {
            return {};
          })
      }
    });
  };
};

export const updateNewExerciseState = newExercise => {
  return dispatch => {
    dispatch({ type: 'UPDATE_EXERCISE', newExercise });
  };
};

export const changeNewInputSentence = data => {
  return dispatch => {
    dispatch({ type: 'CHANGE_INPUT_SENTENCE_DATA', data });
  };
};

export const updateWordState = word => {
  return dispatch => {
    dispatch({ type: 'UPDATE_WORD_STATE', word });
  };
};

export const saveExerciseSolution = newExercise => {
  return dispatch => {
    const { id } = store.getState().auth.user;
    // prelevo dal response la soluzione della punteggiatuera
    console.log(': newExercise.codeSolution', newExercise.codeSolution);

    axios
      .post(
        'http://localhost:8081/exercises/insert-exercise',
        {
          assignedUsersIds: newExercise.studentList
            .filter(student => student.check)
            .map(student => student.id),
          phraseText: newExercise.sentenceString,
          mainSolution: JSON.stringify(newExercise.codeSolution),
          alternativeSolution: JSON.stringify([]),
          visibility: true,
          author: id,
          date: new Date().getTime(),
          language: 'it' // //////////////////////////////DA CAMBIARE
        },
        {
          headers: {
            Authorization: store.getState().auth.token
          }
        }
      )
      .then(res => {
        dispatch({ type: 'SAVE_EXERCISE_SUCCESS', newExercise });
      })
      .catch(() => dispatch({ type: 'LOGIN_ERROR' }));
  };
};

/* {
  "assignedUsersIds": [
    "demoData"
  ],
  "phraseText": "demoData",
  "mainSolution": "demoData",
  "alternativeSolution": "demoData",
  "visibility": true,
  "author": "demoData",
  "date": 1,
  "language": "demoData"
  } */
