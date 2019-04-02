const initState = {
  newExercise: {
    sentence: [],
    sentenceString: '',
    response: null,
    showSolution: false,
    createAt: Date.now(),
    userSolution: [],
    justPunctuationSolution: null
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
      console.log(': initNewExerciseState', action.newExercise);
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
      console.log(': ExerciseReducer -> userSolution', userSolution);
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

    default:
      return state;
  }
};

export default ExerciseReducer;
