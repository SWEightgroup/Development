import axios from 'axios';
import store from '../store/index';
import _translator from '../helpers/Translator';
import { _toastSuccess, _toastError } from '../helpers/Utils';
import { initClass } from './ClassManagementActions';
import { initStateExercise } from './ExerciseActions';
import { initAdmin } from './AdminActions';

export const initAuthStore = () => {
  return dispatch => {
    dispatch({ type: 'INIT_AUTH_STORE' });
  };
};

export const loaderOn = () => {
  return dispatch => {
    dispatch({ type: 'LOADER_ON' });
  };
};

export const loaderOff = () => {
  return dispatch => {
    dispatch({ type: 'LOADER_OFF' });
  };
};

export const displayError = error => {
  return dispatch => {
    dispatch({ type: 'DISPLAY_ERROR', error });
  };
};

export const changeForgot = data => {
  return dispatch => {
    dispatch({ type: 'CHANGE_FORGOT_DATA', data });
  };
};

export const changeSignIn = data => {
  store.dispatch({ type: 'CHANGE_SIGNIN_DATA', data });
};

export const changeSignUp = data => {
  store.dispatch({ type: 'CHANGE_SIGNUP_DATA', data });
};

export const changePassword = ({ requestId, password, passwordConfirm }) => {
  return dispatch => {
    axios
      .post('http://localhost:8081/password/change', {
        requestId,
        password,
        passwordConfirm
      })
      .then(() => {
        dispatch({ type: 'PASSWORD_CHANGED' });
        _toastSuccess(_translator('gen_opSuccess'));
      })
      .catch(() => {
        dispatch(loaderOff());
        _toastError(_translator('gen_error'));
      });
  };
};
export const sendRequest = data => {
  console.log(': data', data);
  return dispatch => {
    axios
      .post('http://localhost:8081/password/forgot', {
        username: data.username
      })
      .then(() => {
        dispatch({ type: 'REQUEST_SENT' });
        _toastSuccess(_translator('gen_opSuccess'));
      })
      .catch(() => {
        dispatch(loaderOff());
        _toastError(_translator('gen_error'));
      });
  };
};

export const signIn = credentials => {
  return dispatch => {
    axios
      .post('http://localhost:8081/login', {
        username: credentials.username,
        password: credentials.password
      })
      .then(res => {
        if (res) {
          localStorage.setItem(
            'token',
            JSON.stringify(res.headers.authorization)
          );
        }
        const userInfo = { user: res.data, token: res.headers.authorization };
        _toastSuccess(_translator('gen_welcome', res.data.language));
        dispatch({ type: 'LOGIN_SUCCESS', userInfo });
      })
      .catch(() => {
        _toastError(_translator('login_error', 'it'));
        dispatch({ type: 'LOGIN_ERROR' });
      });
  };
};

export const signOut = () => {
  localStorage.removeItem('user');
  localStorage.removeItem('token');
  return dispatch => {
    dispatch(initAdmin());
    dispatch(initStateExercise());
    dispatch(initClass());
    dispatch({ type: 'SIGNOUT_SUCCESS' });
  };
};

export const activeAccount = signUpRequestId => {
  return dispatch => {
    dispatch({
      type: 'ACTIVATION',
      payload: {
        message: [_translator('activation_progress_line1')],
        inProgress: true
      }
    });
    dispatch(loaderOn());
    axios
      .get(`http://localhost:8081/sign-up/activate/${signUpRequestId}`)
      .then(res => {
        _toastSuccess(_translator('activation_success_line1'));
        dispatch({
          type: 'ACTIVATION',
          payload: {
            message: [
              _translator('activation_success_line1'),
              _translator('activation_success_line2')
            ],
            inProgress: false
          }
        });
        dispatch(loaderOff());
      })
      .catch(err => {
        dispatch({
          type: 'ACTIVATION',
          payload: {
            message: [
              _translator('activation_err_line1'),
              _translator('activation_err_line2')
            ],
            inProgress: false
          }
        });
        dispatch(loaderOff());
        _toastError('Attivazione fallita');
      });
  };
};

export const signUp = newUser => {
  return dispatch => {
    delete newUser.password_confirm;
    axios
      .post('http://localhost:8081/sign-up', newUser)
      .then(res => {
        dispatch({ type: 'SIGNUP_SUCCESS' });
      })
      .catch(e => {
        console.error(e);
        _toastError(_translator('signUp_error', 'it'));
        dispatch({ type: 'SIGNUP_ERROR' });
      });
  };
};

export const updateUserInfo = user => {
  return dispatch => {
    dispatch(loaderOn());
    axios
      .put(
        'http://localhost:8081/users/modify/',
        {
          ...user
        },
        {
          headers: {
            'content-type': 'application/json',
            Authorization: store.getState().auth.token
          }
        }
      )
      .then(res => {
        _toastSuccess(_translator('gen_opSuccess', res.data.language));

        const userInfo = { user: res.data, token: store.getState().auth.token };
        if (userInfo.user.username !== store.getState().auth.user.username) {
          store.dispatch(signOut());
        } else dispatch({ type: 'LOGIN_SUCCESS', userInfo });
      })
      .catch(err => console.warn(err));
  };
};

export const initializeAuth = () => {
  return dispatch => {
    if (store.getState().auth.token !== null) {
      axios
        .get('http://localhost:8081/users/get-info', {
          headers: {
            Authorization: store.getState().auth.token
          }
        })
        .then(res => {
          // _toastSuccess(_translator('gen_welcome', res.data.language));
          dispatch({ type: 'LOAD_AUTH', user: res.data });
        })
        .catch(error => {
          dispatch(signOut());
          console.error(error);
        });
    } else {
      dispatch(signOut());
    }
  };
};
