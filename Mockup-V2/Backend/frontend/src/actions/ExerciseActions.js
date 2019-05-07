// import axios from 'axios';
import axios from 'axios';
import store from '../store/index';
import _translator from '../helpers/Translator';
import { _toastSuccess, _toastError } from '../helpers/Utils';

export const initStateExercise = () => {
  return dispatch => {
    dispatch({ type: 'INIT_STATE' });
  };
};

export const innerLoaderOn = () => {
  return dispatch => {
    dispatch({ type: 'INNER_LOADER_ON' });
  };
};
export const innerLoaderOff = () => {
  return dispatch => {
    dispatch({ type: 'INNER_LOADER_OFF' });
  };
};

export const initializeNewExercise = () => {
  return dispatch => {
    dispatch({ type: 'INIT_EXERCISE' });
  };
};

export const initNewExerciseState = newExercise => {
  // const allowedPunctuation = /[a-zA-Z]/g;
  return dispatch => {
    dispatch({
      type: 'INIT_NEW_EXERCISE',
      newExercise: {
        ...newExercise,
        language: newExercise.languageSelected,
        userSolution: [
          newExercise.sentence.map(() => {
            return {};
          }),
          newExercise.sentence.map(() => {
            return {};
          })
        ]
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

export const updateWordState = (word, indexSolution) => {
  return dispatch => {
    dispatch({ type: 'UPDATE_WORD_STATE', obj: { word, indexSolution } });
  };
};

export const saveFreeExercise = newExercise => {
  return dispatch => {
    dispatch(innerLoaderOn());
    const { id } = store.getState().auth.user;

    axios
      .post(
        'http://localhost:8081/exercises/student/insert-free-exercise',
        {
          assignedUsersIds: null,
          phraseText: newExercise.sentenceString,
          mainSolution: JSON.stringify(newExercise.codeSolution[0]),
          alternativeSolution: '',
          visibility: true,
          visibilityDev: !newExercise.privateExerciseDev,
          author: id,
          date: new Date().getTime(),
          language: 'it'
        },
        {
          headers: {
            'content-type': 'application/json',
            Authorization: store.getState().auth.token
          }
        }
      )
      .then(res => {
        dispatch({ type: 'SAVE_EXERCISE_SUCCESS', newExercise });
      })
      .catch(() => {
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const saveSolution = newExercise => {
  return dispatch => {
    dispatch(innerLoaderOn());
    axios
      .post(
        'http://localhost:8081/exercises/student/do',
        {
          solutionFromStudent: JSON.stringify(newExercise.codeSolution[0]),
          exerciseId: newExercise.id
        },
        {
          headers: {
            'content-type': 'application/json',
            Authorization: store.getState().auth.token
          }
        }
      )
      .then(res => {
        const { solutionText, mark, reliability } = res.data;
        const objSolution = JSON.parse(solutionText);

        dispatch({
          type: 'SAVE_EXERCISE_SUCCESS',
          newExercise: {
            response: objSolution.map(item => {
              return { tag: item };
            }),
            mark,
            reliability
          }
        });
      })
      .catch(() => {
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const saveExerciseSolution = newExercise => {
  return dispatch => {
    dispatch(innerLoaderOn());
    const { id } = store.getState().auth.user;
    // prelevo dal response la soluzione della punteggiatuera

    axios
      .post(
        'http://localhost:8081/exercises/insert-exercise',
        {
          assignedUsersIds: store
            .getState()
            .class.studentsList.filter(student => student.check)
            .map(student => student.id),
          phraseText: newExercise.sentenceString,
          mainSolution: JSON.stringify(newExercise.codeSolution[0]),
          alternativeSolution: newExercise.alternativeSolution
            ? JSON.stringify(newExercise.codeSolution[1])
            : '',
          visibility: !newExercise.privateExercise,
          visibilityDev: !newExercise.privateExerciseDev,
          author: id,
          date: new Date().getTime(),
          language: 'it'
        },
        {
          headers: {
            'content-type': 'application/json',
            Authorization: store.getState().auth.token
          }
        }
      )
      .then(res => {
        _toastSuccess(
          _translator('gen_insertDone', store.getState().auth.user.language)
        );

        dispatch({ type: 'SAVE_EXERCISE_SUCCESS', newExercise });
      })
      .catch(err => {
        console.error(err);
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const loadTodoExercises = _link => {
  const link =
    _link !== null && _link !== undefined
      ? _link.href
      : 'http://localhost:8081/exercises/todo';
  return dispatch => {
    dispatch(innerLoaderOn());
    axios
      .get(link, {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        dispatch({ type: 'LOAD_TODO_SUCCESS', todo: res.data });
      })
      .catch(err => {
        console.error(err);
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const loadDoneExercises = _link => {
  let path = '';
  if (store.getState().auth.user.role === 'ROLE_TEACHER') path = 'added';
  else path = 'done';
  const link =
    _link !== null && _link !== undefined
      ? _link.href
      : `http://localhost:8081/exercises/${path}`;
  return dispatch => {
    dispatch(innerLoaderOn());
    axios
      .get(link, {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        dispatch({ type: 'LOAD_DONE_SUCCESS', todo: res.data });
      })
      .catch(err => {
        console.error(err);
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const loadPublicExercises = ({ _link, onlyFavourite }) => {
  const defaultLink = onlyFavourite
    ? 'http://localhost:8081/exercises/favourite-public'
    : 'http://localhost:8081/exercises/public';
  const link = _link !== null && _link !== undefined ? _link.href : defaultLink;
  return dispatch => {
    dispatch(innerLoaderOn());
    axios
      .get(link, {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        dispatch({ type: 'LOAD_PUBLIC_SUCCESS', public: res.data });
      })
      .catch(err => {
        console.error(err);
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const getAutomaticSolution = sentenceString => {
  return dispatch => {
    axios
      .post(
        'http://localhost:8081/exercises/automatic-solution',
        {
          text: sentenceString.trim()
        },
        {
          headers: {
            Authorization: store.getState().auth.token
          }
        }
      )
      .then(res => {
        dispatch(
          updateNewExerciseState({
            response: JSON.parse(res.data.solutionText).sentences[0].tokens,
            language: store.getState().exercise.newExercise.languageSelected
            // .filter(token => token.tag.charAt(0) !== 'F')
          })
        );
        dispatch(innerLoaderOff());
      })
      .catch(err => {
        console.error(err);
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const getExerciseDetails = exerciseId => {
  return dispatch => {
    dispatch(innerLoaderOn());
    axios
      .get(`http://localhost:8081/exercises/${exerciseId}`, {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        dispatch({ type: 'EXERCISE_DETAILS', exercise: res.data });
        dispatch(innerLoaderOff());
      })
      .catch(err => {
        console.error(err);
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const initExerciseDetails = () => {
  return dispatch => {
    dispatch({ type: 'INIT_EXERCISE_DETAILS' });
  };
};

export const changePublicExerciseFilter = onlyFavourite => {
  return dispatch => {
    dispatch(
      loadPublicExercises({
        onlyFavourite
      })
    );
    dispatch({ type: 'CHANGE_PUBLIC_EXERCISE_FILTER' });
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
