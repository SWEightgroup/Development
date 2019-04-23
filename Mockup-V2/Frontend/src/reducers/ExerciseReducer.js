const initState = {
  newExercise: {
    sentence: [],
    sentenceString: '',
    response: null,
    showSolution: false,
    createAt: Date.now(),
    userSolution: [], // lista di stati per le word
    codeSolution: [],
    complete: false
  },
  studentList: [],
  todoExercises: { exercises: null, page: null, links: null },
  doneExercises: { exercises: null, page: null, links: null },
  publicExercises: { exercises: null, page: null, links: null },
  innerLoader: false
};

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
    case 'UPDATE_STUDENT_LIST':
      return {
        ...state,
        studentList: action.studentList
      };
    case 'UPDATE_WORD_STATE':
      const userSolution = state.newExercise.userSolution;
      userSolution[action.word.index] = action.word;
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
        studentList: state.studentList,
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
    default:
      // console.error('REDUCER ERRATO', state, action);
      return { ...state, innerLoader: false };
  }
};

export default ExerciseReducer;
