import axios from 'axios';
import authReducer from '../../reducers/AuthReducer';
import * as actions from '../../actions/AuthActions';

jest.mock('axios');

describe('authenticate reducer', () => {
  const initState = {
    activationInProgress: false,
    activationMessage: [],
    signUpCompleted: false,
    signinError: null,
    signupError: null,
    isReady: false,
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

  const LoggedState = {
    activationInProgress: false,
    activationMessage: [],
    signUpCompleted: false,
    signinError: null,
    signupError: null,
    isReady: false,
    user: {
      // che ci va messo in btn???
      btAction: '',
      firstName: 'TestName',
      id: '12345456667',
      language: 'it',
      lastName: 'TestLastName',
      role: 'ROLE_STUDENT',
      username: 'TestEmail'
    },
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

  // const userData = {
  //   btAction: '',
  //   firstName: 'TestName',
  //   id: '12345456667',
  //   language: 'it',
  //   lastName: 'TestLastName',
  //   role: 'ROLE_STUDENT',
  //   username: 'TestEmail'
  // };

  it('reducers initial state ready', () => {
    expect(authReducer(initState, { type: 'INIT_AUTH_STORE' })).toEqual({
      ...initState,
      isReady: true
    });
  });

  it('reducers CHANGE_SIGNIN_DATA', () => {
    expect(authReducer(initState, { type: 'CHANGE_SIGNIN_DATA' })).toEqual({
      ...initState,
      signIn: { ...initState.signIn, ...actions.data }
    });
  });

  it('reducers CHANGE_SIGNUP_DATA', () => {
    expect(authReducer(initState, { type: 'CHANGE_SIGNUP_DATA' })).toEqual({
      ...initState,
      signUp: { ...initState.signUp, ...actions.data }
    });
  });

  it('reducer LOADER_ON', () => {
    expect(authReducer(initState, { type: 'LOADER_ON' })).toEqual({
      ...initState,
      loader: true
    });
  });

  it('reducer LOAD_AUTH', () => {
    expect(authReducer(LoggedState, { type: 'LOAD_AUTH' })).toEqual({
      ...initState,
      user: actions.user,
      loader: false,
      isReady: true
    });
  });

  it('reducer LOGIN_ERROR', () => {
    expect(authReducer(initState, { type: 'LOGIN_ERROR' })).toEqual({
      ...initState,
      signinError: 'Login failed',
      signupError: null,
      user: null,
      loader: false,
      isReady: true
    });
  });

  it('reducer LOGIN_SUCCESS', () => {
    const res = {
      data: {
        accountNonExpired: true,
        accountNonLocked: true,
        authorities: 'ROLE_DEVELOPER',
        credentialsNonExpired: true,
        dateOfBirth: '1999-12-31T00:00:00.000+0000',
        enabled: true,
        firstName: 'Cane',
        id: '5ccbf41e476bb2463c6ecf24',
        language: 'it',
        lastName: 'Gatto',
        role: 'ROLE_DEVELOPER',
        username: 'dev@colletta.com'
      }
    };

    axios.post.mockImplementationOnce(() => Promise.resolve(res));
    // expect(componentDidMountSpy).toHaveBeenCalled();
    const expectedActions = {
      type: 'LOGIN_SUCCESS',
      userInfo: {
        token:
          'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBjb2xsZXR0YS5pdCIsImV4cCI6MTU1ODA4MjU4MSwianRpIjoiNWNjZjE3MGYwNGRhY2IxMjc0MGY2ZGM3In0.EWfEeORk3UhRdBy3ZdZ6_fs4HxSDEACjSxqgOQ8ngQpWFEFxLoYUeneLfdkk776K2b82ZjLlW1SWiSvP4dZFNQ',
        user: {
          accountNonExpired: true,
          accountNonLocked: true,
          authorities: { authority: 'ROLE_ADMIN' },
          credentialsNonExpired: true,
          currentGoal: 0,
          dateOfBirth: 1388534400000,
          enabled: true,
          favoriteTeacherIds: [],
          firstName: 'Pippo',
          id: '5ccf170f04dacb12740f6dc7',
          language: 'it',
          lastName: 'Rossi',
          role: 'ROLE_ADMIN',
          username: 'admin@colletta.it'
        }
      }
    };
    // const credentials = { username: 'fakeuser@lol.it', password: 'fakwpwd' };
    expect(authReducer(initState, expectedActions)).toEqual({
      ...initState,
      user: expectedActions.userInfo.user,
      token: expectedActions.userInfo.token,
      signIn: LoggedState.signIn,
      loader: false,
      signUp: LoggedState.signUp,
      isReady: true,
      signUpCompleted: false
    });
  });

  it('reducer SIGNOUT_SUCCESS', () => {
    expect(authReducer(initState, { type: 'SIGNOUT_SUCCESS' })).toEqual({
      ...initState,
      isReady: true
    });
  });

  it('reducer SIGNUP_ERROR', () => {
    expect(authReducer(initState, { type: 'SIGNUP_ERROR' })).toEqual({
      ...initState,
      signupError: 'SignUp failed',
      user: null,
      loader: false,
      isReady: true,
      signUpCompleted: false
    });
  });

  it('reducer SIGNUP_SUCCESS', () => {
    expect(authReducer(initState, { type: 'SIGNUP_SUCCESS' })).toEqual({
      ...initState,
      signUpCompleted: true,
      isReady: true
    });
  });

  it('reducer DISPLAY_ERROR', () => {
    expect(authReducer(initState, { type: 'DISPLAY_ERROR' })).toEqual({
      ...initState,
      authError: actions.error,
      loader: false
    });
  });
});
