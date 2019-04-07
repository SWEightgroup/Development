import axios from 'axios';
import { toast } from 'react-toastify';
import { store } from '../index';
import _translator from '../helpers/Translator';

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
        toast.success(_translator('gen_welcome', res.data.language), {
          position: toast.POSITION.TOP_CENTER,
          hideProgressBar: true,
          className: {
            color: '#343a40',
            minHeight: '60px',
            borderRadius: '8px',
            background: '#2FEDAD',
            boxShadow: '2px 2px 20px 2px rgba(0,0,0,0.3)'
          },
        });
        dispatch({ type: 'LOGIN_SUCCESS', userInfo });
      })
      .catch(e => {
        toast.error(`Login error`, {
          position: toast.POSITION.BOTTOM_CENTER
        });
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
  delete newUser.password_confirm;
  return dispatch => {
    axios
      .post('http://localhost:8081/users/sign-up', newUser)
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
          toast.success(_translator('gen_welcome', res.data.language), {
            position: toast.POSITION.TOP_CENTER,
            hideProgressBar: true
          });
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
