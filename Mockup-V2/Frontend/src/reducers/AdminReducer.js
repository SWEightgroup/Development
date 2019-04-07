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
    case 'USER_DELETE_SUCCES':
      const { id } = action.payload;
      const { usersList, devList } = state;
      const newUsersList = usersList.filter(user => user.id !== id);
      const newDevList = devList.filter(user => user.id !== id);
      return {
        ...state,
        usersList: newUsersList,
        devList: newDevList
      };
    default:
      return { ...state, loader: false };
  }
};

export default AdminReducer;
