const initState = {
  authError: null,
  user: null,
  loader: false
};

const authReducer = (state = initState, action) => {
  console.log(action.type);
  switch (action.type) {
    case 'LOADER_ON':
      return {
        ...state,
        loader: true
      };
    case 'LOAD_AUTH':
      return {
        ...state,
        user: action.user
      };
    case 'LOGIN_ERROR':
      return {
        ...state,
        authError: 'Login failed',
        user: null,
        loader: false
      };
    case 'LOGIN_SUCCESS':
      return {
        ...state,
        user: action.user,
        loader: false
      };

    case 'SIGNOUT_SUCCESS':
      return {
        ...state,
        authError: null,
        user: null,
        loader: false
      };
    case 'SIGNUP_SUCCESS':
      return {
        ...state,
        user: action.user,
        loader: false
      };

    case 'SIGNUP_ERROR':
      return {
        ...state,
        authError: 'Sing up failed',
        user: null,
        loader: false
      };

    default:
      return state;
  }
};

export default authReducer;
