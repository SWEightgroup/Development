const initState = {
  authError: null,
  user: null,
  loader: false,
  signIn: {
    email: '',
    password: ''
  },
  signUp: {
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    password_confirm: ''
  },
  dataModify: {
    email: '',
    password: '',
    firstName: '',
    lastName: ''
  },
  arrayWord: []
};

const authReducer = (state = initState, action) => {
  switch (action.type) {
    case 'CHANGE_SIGNIN_DATA':
      const signIn = { ...state.signIn, ...action.data };
      return {
        ...state,
        signIn
      };
    case 'CHANGE_SIGNUP_DATA':
      const signUp = { ...state.signUp, ...action.data };
      return {
        ...state,
        signUp
      };
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
        signIn: initState.signIn,
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
        signUp: initState.signUp,
        loader: false
      };

    case 'SIGNUP_ERROR':
      return {
        ...state,
        authError: 'Sing up failed',
        user: null,
        loader: false
      };

    case 'DISPLAY_ERROR':
      return {
        ...state,
        authError: action.error
      };

    default:
      return state;
  }
};

export default authReducer;
