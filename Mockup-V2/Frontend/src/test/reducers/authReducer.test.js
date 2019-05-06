import authReducer from '../../reducers/AuthReducer';
import * as actions from '../../actions/AuthActions';

describe('authenticate reducer', () => {
  const initState = {
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

  // it('reducer LOGIN_SUCCESS', () => {
  //   expect(authReducer(initState, { type: 'LOGIN_SUCCESS' })).toEqual({
  //     ...initState,
  //     user: actions.userInfo.user,
  //     token: actions.userInfo.token,
  //     signIn: initState.signIn,
  //     loader: false,
  //     signUp: initState.signUp,
  //     isReady: true,
  //     signUpCompleted: false
  //   });
  // });

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

// describe('auth.ROLE', () => {
//   it('ROLE in AdminDevDashboard: Admin', () => {
//     const user = {
//       accountNonExpired: true,
//       accountNonLocked: true,
//       credentialsNonExpired: true,
//       dateOfBirth: '2018-01-01T00:00:00.000+0000',
//       enabled: true,
//       firstName: 'Admin',
//       id: '5cc59b407d6a042803148717',
//       language: 'en',
//       lastName: 'admin',
//       role: 'ROLE_ADMIN',
//       username: 'admin@admin.com'
//     };
//     const admin = {
//       devList: [],
//       usersList: []
//     };
//     // const fetchDeveloperListDispatch = ;

//     const { language } = user;
//     const { devList } = admin;

//     const wrapperAdmin = mount(
//       <ProtectedRoute
//         path="/developers-management"
//         isAllowed={user && user.role === 'ROLE_ADMIN'}
//         component={AdminDevDashBoard}
//       />
//     );

//     expect(wrapperAdmin);
//   });
// });
