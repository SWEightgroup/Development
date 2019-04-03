const initState = {
  newExercise: {
    sentence: [],
    sentenceString: '',
    response: null,
    showSolution: false,
    createAt: Date.now(),
    userSolution: [],
    justPunctuationSolution: null,
    complete: false,
    studentList: []
  }
};

const ExerciseReducer = (state = initState, action) => {
  switch (action.type) {
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
        newExercise: action.newExercise
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
      console.log(': ExerciseReducer -> state', state);
      return {
        ...state,
        newExercise: initState.newExercise
      };

    default:
      //  console.error('REDUCER ERRATO', state, action);
      return state;
  }
};

export default ExerciseReducer;
