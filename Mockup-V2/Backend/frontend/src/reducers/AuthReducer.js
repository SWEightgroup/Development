const initState = {
  signinError: null,
  signupError: null,
  user: null,
  loader: false,
  signIn: {
    username: '',
    password: ''
  },
  signUp: {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    role: '',
    language: '',
    dateOfBirth: '',
    password_confirm: ''
  },
  dataModify: {
    username: '',
    password: '',
    firstName: '',
    lastName: ''
  },
  token: localStorage.getItem('token')
    ? JSON.parse(localStorage.getItem('token'))
    : null
};

const authReducer = (state = initState, action) => {
  switch (action.type) {
    case 'CHANGE_SIGNIN_DATA':
      return {
        ...state,
        signIn: { ...state.signIn, ...action.data }
      };
    case 'CHANGE_SIGNUP_DATA':
      return {
        ...state,
        signUp: { ...state.signUp, ...action.data }
      };
    case 'LOADER_ON':
      return {
        ...state,
        loader: true
      };
    case 'LOAD_AUTH':
      return {
        ...state,
        user: action.user,
        loader: false
      };
    case 'LOGIN_ERROR':
      return {
        ...state,
        signinError: 'Login failed',
        signupError: null,
        user: null,
        loader: false
      };
    case 'LOGIN_SUCCESS':
      return {
        ...state,
        user: action.userInfo.user,
        token: action.userInfo.token,
        signIn: initState.signIn,
        loader: false,
        signUp: initState.signUp
      };

    case 'SIGNOUT_SUCCESS':
      return {
        ...state,
        signinError: null,
        signupError: null,
        user: null,
        token: null,
        loader: false
      };

    case 'SIGNUP_ERROR':
      return {
        ...state,
        signupError: 'SignUp failed',
        user: null,
        loader: false
      };

    case 'DISPLAY_ERROR':
      return {
        ...state,
        authError: action.error,
        loader: false
      };

    default:
      return { ...state, loader: false };
  }
};

export default authReducer;
