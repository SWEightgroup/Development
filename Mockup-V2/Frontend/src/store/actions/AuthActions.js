import axios from 'axios';

export const loaderOn = () => {
  return dispatch => {
    dispatch({ type: 'LOADER_ON' });
  };
};

export const loadAuth = pippo => {
  return dispatch => {
    dispatch({ type: 'LOAD_AUTH', user: pippo });
  };
};

export const signIn = credentials => {
  return dispatch => {
    axios
      .post(`http://localhost:8081/sw/login`, {
        email: credentials.email,
        password: credentials.password
      })
      .then(res => {
        console.log('ciao', res);
        if (res.data.entity)
          localStorage.setItem('user', JSON.stringify(res.data.entity));
        dispatch({ type: 'LOGIN_SUCCESS', user: res.data.entity });
      })
      .catch(() => dispatch({ type: 'LOGIN_ERROR' }));
  };
};

export const signOut = () => {
  localStorage.removeItem('user');
  return (dispatch, getState) => {
    dispatch({ type: 'SIGNOUT_SUCCESS' });
  };
};

export const signUp = newUser => {
  return (dispatch, getState) => {
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
