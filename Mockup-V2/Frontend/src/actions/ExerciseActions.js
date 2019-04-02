// import axios from 'axios';
import axios from 'axios';
import { store } from '../index';

export const initializeNewExercise = () => {
  return dispatch => {
    dispatch({ type: 'INIT_EXERCISE' });
  };
};

export const initNewExerciseState = newExercise => {
  return dispatch => {
    dispatch({
      type: 'INIT_NEW_EXERCISE',
      newExercise: {
        ...newExercise,
        userSolution: newExercise.sentence.map(() => [])
      }
    });
  };
};

export const updateNewExerciseState = newExercise => {
  console.log(': updateNewExerciseState', newExercise);

  return dispatch => {
    dispatch({ type: 'UPDATE_EXERCISE', newExercise });
  };
};

export const changeNewInputSentence = data => {
  console.log(': changeNewInputSentence');
  return dispatch => {
    dispatch({ type: 'CHANGE_INPUT_SENTENCE_DATA', data });
  };
};

export const updateWordState = word => {
  console.log(': updateWordState', word);
  return dispatch => {
    dispatch({ type: 'UPDATE_WORD_STATE', word });
  };
};

export const saveExerciseSolution = newExercise => {
  const { id } = store.getState().auth.user;

  /*   const wordSolution = null;
  const codeSolution = newExercise.userSolution.map(wordState => {
    if (wordState.languageIterator)
      return wordState.languageIterator.getCodeSolution();
    return '';
  });
  console.log(': codeSolution', codeSolution);
  const solutionToSubmit = newExercise.justPunctuationSolution.map(position => {
    if (position === null) return wordSolution.shift();
    return position;
  }); */
  // //////////////todooooooooooooooooooooooooooooooooooooooo

  axios
    .post(
      'http://localhost:8081/exercises/insert-exercise',
      {
        assignedUsersIds: [],
        phraseText: newExercise.sentenceString,
        mainSolution: 'Soluzione Provvisoria',
        alternativeSolution: '',
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
      console.log(': res', res);
      return dispatch => dispatch({ type: 'saveExerciseSuccess', newExercise });
    })
    .catch(() => dispatch => dispatch({ type: 'LOGIN_ERROR' }));
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
