const initState = {
  authError: null,
  token: null,
  auth: null,
  loader: false
};

const authReducer = (state = initState, action) => {
  switch (action.type) {
    case 'LOADER_ON':
      return {
        ...state,
        loader: true
      };
    case 'GET_TOKEN_SUCCESS':
      return {
        ...state,
        authError: null,
        token: action.token,
        loader: false
      };
    case 'GET_TOKEN_ERROR':
      return {
        ...state,
        authError: 'Login failed',
        loader: false
      };
    case 'LOGIN_ERROR':
      return {
        ...state,
        authError: 'Login failed',
        auth: null,
        loader: false
      };
    case 'LOGIN_SUCCESS':
      return {
        ...state,
        auth: action.auth,
        loader: false
      };

    case 'SIGNOUT_SUCCESS':
      return {
        ...state,
        authError: null,
        auth: null,
        token: null,
        loader: false
      };

    case 'SIGNUP_SUCCESS':
      return {
        ...state,
        authError: null,
        token: action.token,
        loader: false
      };

    case 'SIGNUP_ERROR':
      return {
        ...state,
        authError: 'Sing up failed',
        token: null,
        auth: null,
        loader: false
      };

    default:
      return state;
  }
};

export default authReducer;
