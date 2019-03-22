const initState = {
  newExercise: {
    sentence: [],
    sentenceString: '',
    response: null,
    showSolution: false,
    createAt: Date.now()
  }
};

const ExerciseReducer = (state = initState, action) => {
  switch (action.type) {
    case 'INIT_EXERCISE':
      return {
        ...state,
        newExercise: initState.newExercise
      };
    case 'UPDATE_EXERCISE':
      return {
        ...state,
        newExercise: action.newExercise
      };
    case 'CHANGE_INPUT_SENTENCE_DATA':
      const newExercise = {
        ...state.newExercise,
        sentenceString: action.data
      };
      return {
        ...state,
        newExercise
      };
    case 'PREPARE_EXERCISE_DATA':
      const sentenceArray = state.sentenceString.split(' ');
      return {
        ...state,
        sentence: sentenceArray
      };

      return {
        ...state,
        newExercise
      };
    default:
      return state;
  }
};

export default ExerciseReducer;
