const initState = {};

const issueReducer = (state = initState, action) => {
  switch (action.type) {
    case "REASSIGN_ISSUE_SUCCESS":
      return state;
    case "REASSIGN_ISSUE_ERROR":
      return state;
    case "CREATE_UC_SUCCESS":
      return state;
    case "CREATE_UC_ERROR":
      return state;
    case "UPDATE_UC_SUCCESS":
      console.log("aggiornato uc");
      return state;
    case "UPDATE_UC_ERROR":
      console.log("errore aggiornamento uc");
      return state;
    default:
      return state;
  }
};

export default issueReducer;
