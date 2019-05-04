/* eslint-disable no-case-declarations */
const initState = {
  newExercise: {
    sentence: [],
    sentenceString: '',
    response: null,
    showSolution: false,
    createAt: Date.now(),
    userSolution: [[], []], // lista di stati per le word
    codeSolution: [[], []],
    complete: false,
    alternativeSolution: false
  },
  todoExercises: { exercises: null, page: null, links: null },
  doneExercises: { exercises: null, page: null, links: null },
  publicExercises: { exercises: null, page: null, links: null },
  onlyFavouritePublicExercise: false,
  innerLoader: false,
  exerciseDetails: null
};

/* EXERCISE MODEL
{
  alternativeSolutionId: "5cca155d04dacb44349032c9"
  authorId: "5cca0e4204dacb3c58a49caa"
  authorName: "Margherita Visentin"
  dateExercise: 1556747727951
  id: "5cca15cf04dacb44349032cb"
  mainSolutionId: "5cca155204dacb44349032c8"
  phraseId: "5cca15a304dacb44349032ca"
  phraseText: "Questo è un nuovo esercizio per gli studenti"
  studentIdDone: []
  studentIdToDo: ["5cca0dfb04dacb3c58a49ca8", "5cca0e7d04dacb3c58a49cad"]
  visibility: true
}

*/

const ExerciseReducer = (state = initState, action) => {
  switch (action.type) {
    case 'INIT_STATE':
      return {
        ...initState
      };
    case 'INNER_LOADER_ON':
      return {
        ...state,
        innerLoader: true
      };
    case 'INNER_LOADER_OFF':
      return {
        ...state,
        innerLoader: false
      };
    case 'INIT_EXERCISE':
      return {
        ...state,
        newExercise: initState.newExercise
      };
    case 'INIT_NEW_EXERCISE':
      return {
        ...state,
        newExercise: action.newExercise
      };
    case 'UPDATE_EXERCISE':
      return {
        ...state,
        newExercise: { ...state.newExercise, ...action.newExercise }
      };

    case 'UPDATE_WORD_STATE':
      const { word, indexSolution } = action.obj;
      const userSolution = state.newExercise.userSolution[indexSolution];

      userSolution[word.index] = word;
      return {
        ...state,
        newExercise: { ...state.newExercise }
      };

    case 'CHANGE_INPUT_SENTENCE_DATA':
      return {
        ...state,
        newExercise: {
          ...state.newExercise,
          sentenceString: action.data
        }
      };
    case 'SAVE_EXERCISE_SUCCESS':
      return {
        ...initState,
        newExercise: {
          ...state.newExercise,
          ...action.newExercise,
          showSolution: true
        },
        innerLoader: false
        // newExercise: initState.newExercise
      };

    case 'LOAD_TODO_SUCCESS':
      return {
        ...state,
        todoExercises: {
          exercises: action.todo._embedded
            ? action.todo._embedded.exerciseModels
            : [],
          page: action.todo.page,
          links: action.todo._links
        },
        innerLoader: false
      };
    case 'LOAD_DONE_SUCCESS':
      return {
        ...state,
        doneExercises: {
          exercises: action.todo._embedded
            ? action.todo._embedded.exerciseModels
            : [],
          page: action.todo.page,
          links: action.todo._links
        },
        innerLoader: false
      };
    case 'LOAD_PUBLIC_SUCCESS':
      return {
        ...state,
        publicExercises: {
          exercises: action.public._embedded
            ? action.public._embedded.exerciseModels
            : [],
          page: action.public.page,
          links: action.public._links
        },
        innerLoader: false
      };
    case 'CHANGE_PUBLIC_EXERCISE_FILTER':
      return {
        ...state,
        onlyFavouritePublicExercise: !state.onlyFavouritePublicExercise
      };
    case 'EXERCISE_DETAILS':
      return {
        ...state,
        exerciseDetails: action.exercise
      };
    case 'INIT_EXERCISE_DETAILS':
      return {
        ...state,
        exerciseDetails: initState.exerciseDetails
      };
    default:
      // console.error('REDUCER ERRATO', state, action);
      return { ...state, innerLoader: false };
  }
};

export default ExerciseReducer;
