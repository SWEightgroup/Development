import axios from 'axios';

export const loaderOn = () => {
  return (dispatch, getState) => {
    dispatch({ type: 'LOADER_ON' });
  };
};

export const loadAuth = () => {
  return (dispatch, getState) => {
    dispatch({ type: 'LOAD_AUTH' });
  };
};

export const signIn = credentials => {
  return (dispatch, getState) => {
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
        localStorage.setItem('user', JSON.stringify(res.data));
        dispatch({ type: 'SIGNUP_SUCCESS', user: res.data });
      })
      .catch(() => dispatch({ type: 'SIGNUP_ERROR' }));
  };
};
