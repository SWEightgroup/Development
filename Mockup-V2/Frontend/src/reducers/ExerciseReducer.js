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
  todoExercises: null,
  doneExercises: null,
  innerLoader: false
};

const ExerciseReducer = (state = initState, action) => {
  switch (action.type) {
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
        // ...state,
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
        todoExercises: action.todo,
        innerLoader: false
      };
    case 'LOAD_DONE_SUCCESS':
      return {
        ...state,
        doneExercises: action.todo,
        innerLoader: false
      };
    default:
      // console.error('REDUCER ERRATO', state, action);
      return { ...state, innerLoader: false };
  }
};

export default ExerciseReducer;
