import axios from 'axios';
import { store } from '../index';

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

/* export const loadAuth = pippo => {
  return dispatch => {
    dispatch({ type: 'LOAD_AUTH', user: pippo });
  };
};
*/
export const changeSignIn = data => {
  store.dispatch({ type: 'CHANGE_SIGNIN_DATA', data });
};

export const changeSignUp = data => {
  store.dispatch({ type: 'CHANGE_SIGNUP_DATA', data });
};

export const signIn = () => {
  const credentials = store.getState().auth.signIn;
  return dispatch => {
    axios
      .post(`http://localhost:8081/sw/login`, {
        username: credentials.username,
        password: credentials.password
      })
      .then(res => {
        if (res) localStorage.setItem('user', JSON.stringify(res.data.entity));
        dispatch({ type: 'LOGIN_SUCCESS', user: res.data.entity });
      })
      .catch(() => dispatch({ type: 'LOGIN_ERROR' }));
  };
};

export const signOut = () => {
  localStorage.removeItem('user');
  return dispatch => {
    dispatch({ type: 'SIGNOUT_SUCCESS' });
  };
};

export const signUp = () => {
  const newUser = store.getState().auth.signUp;
  delete newUser.password_confirm;
  return dispatch => {
    axios
      .post(`http://localhost:8081/sw/nu`, newUser)
      .then(res => {
        if (res.data.entity)
          localStorage.setItem('user', JSON.stringify(res.data.entity));
        dispatch({ type: 'SIGNUP_SUCCESS', user: res.data.entity });
      })
      .catch(() => dispatch({ type: 'SIGNUP_ERROR' }));
  };
};

export const updateUserInfo = data => {
  /* CONTROLLI */
  /* CHIAMATA ALLA BACKEND */
  /* CHIAMATA A MODIFY_SUCCES O MODIFY_FAILED */
};
