const initState = {
  devList: [],
  usersList: []
};

const AdminReducer = (state = initState, action) => {
  switch (action.type) {
    case 'UPDATE_DEV_LIST':
      return {
        ...state,
        devList: action.payload
      };
    case 'UPDATE_USER_LIST':
      return {
        ...state,
        usersList: action.payload
      };
    default:
      return { ...state, loader: false };
  }
};

export default AdminReducer;
