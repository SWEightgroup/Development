const initState = {
  signinError: null,
  signupError: null,
  user: localStorage.getItem('user')
    ? JSON.parse(localStorage.getItem('user'))
    : null,
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
  token: null
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
      console.log(': authReducer -> signUp', signUp);

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
        authError: action.error
      };

    default:
      return state;
  }
};

export default authReducer;
