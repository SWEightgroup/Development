const initState = {
  devList: []
};

const AdminReducer = (state = initState, action) => {
  switch (action.type) {
    case 'UPDATE_USER_LIST':
      return {
        ...state,
        devList: action.payload
      };
    default:
      return { ...state, loader: false };
  }
};

export default AdminReducer;
