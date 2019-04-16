import axios from 'axios';
import { store } from '../index';
import _translator from '../helpers/Translator';
import { _toastSuccess, _toastError } from '../helpers/Utils';

export const loaderOn = () => {
  return dispatch => {
    dispatch({ type: 'LOADER_ON' });
  };
};

export const displayError = error => {
  return dispatch => {
    dispatch({ type: 'DISPLAY_ERROR', error });
  };
};

export const changeSignIn = data => {
  store.dispatch({ type: 'CHANGE_SIGNIN_DATA', data });
};

export const changeSignUp = data => {
  store.dispatch({ type: 'CHANGE_SIGNUP_DATA', data });
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
      .catch(e => {
        _toastError('Autenticazione fallita');
        dispatch({ type: 'LOGIN_ERROR' });
      });
  };
};

export const signOut = () => {
  localStorage.removeItem('user');
  localStorage.removeItem('token');
  return dispatch => {
    dispatch({ type: 'SIGNOUT_SUCCESS' });
  };
};

export const signUp = newUser => {
  return dispatch => {
    delete newUser.password_confirm;
    axios
      .post('http://localhost:8081/sign-up', newUser)
      .then(res => {
        dispatch(
          signIn({ username: newUser.username, password: newUser.password })
        );
      })
      .catch(() => dispatch({ type: 'SIGNUP_ERROR' }));
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
        const userInfo = { user: res.data, token: store.getState().auth.token };

        if (userInfo.user.username !== user.username) store.dispatch(signOut());
        else dispatch({ type: 'LOGIN_SUCCESS', userInfo });
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
          _toastSuccess(_translator('gen_welcome', res.data.language));
          dispatch({ type: 'LOAD_AUTH', user: res.data });
        })
        .catch(error => {
          dispatch(signOut());
          console.log(error);
        });
    } else {
      dispatch(signOut());
    }
  };
};
