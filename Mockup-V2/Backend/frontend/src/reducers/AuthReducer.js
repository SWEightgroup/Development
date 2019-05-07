const initState = {
  signUpCompleted: false,
  signinError: null,
  signupError: null,
  isReady: false,
  user: null,
  loader: false,
  activationInProgress: false,
  activationMessage: [],
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
    case 'INIT_AUTH_STORE':
      return { ...initState, isReady: true };
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
    case 'LOADER_OFF':
      return {
        ...state,
        loader: false
      };
    case 'ACTIVATION':
      return {
        ...state,
        activationInProgress: action.payload.inProgress,
        activationMessage: action.payload.message
      };
    case 'LOAD_AUTH':
      return {
        ...state,
        user: action.user,
        loader: false,
        isReady: true
      };
    case 'LOGIN_ERROR':
      return {
        ...state,
        signinError: 'Login failed',
        signupError: null,
        user: null,
        loader: false,
        isReady: true
      };
    case 'LOGIN_SUCCESS':
      return {
        ...state,
        user: action.userInfo.user,
        token: action.userInfo.token,
        signIn: initState.signIn,
        loader: false,
        signUp: initState.signUp,
        isReady: true,
        signUpCompleted: false
      };

    case 'SIGNOUT_SUCCESS':
      return { ...initState, isReady: true };

    case 'SIGNUP_ERROR':
      return {
        ...state,
        signupError: 'SignUp failed',
        user: null,
        loader: false,
        isReady: true,
        signUpCompleted: false
      };
    case 'SIGNUP_SUCCESS':
      return {
        ...initState,
        signUpCompleted: true,
        isReady: true
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
