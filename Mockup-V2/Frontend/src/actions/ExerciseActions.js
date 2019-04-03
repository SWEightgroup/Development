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

  const userSolution = newExercise.userSolution.map(word => word.solution); // questo è un array di codici che invio al backend

  console.log('listaaaaaaaaaaaaaaaaaa', newExercise.studentList);

  console.log({
    assignedUsersIds: [],
    phraseText: newExercise.sentenceString,
    mainSolution: JSON.stringify(userSolution),
    alternativeSolution: null,
    visibility: true,
    author: id,
    date: new Date().getTime(),
    language: 'it' // //////////////////////////////DA CAMBIARE
  });
  axios
    .post(
      'http://localhost:8081/exercises/insert-exercise',
      {
        assignedUsersIds: [],
        phraseText: newExercise.sentenceString,
        mainSolution: JSON.stringify(userSolution),
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

      store.dispatch({ type: 'SAVE_EXERCISE_SUCCESS', newExercise });
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
