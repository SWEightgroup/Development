import axios from 'axios';

export const loaderOn = () => {
  return (dispatch, getState) => {
    dispatch({ type: 'LOADER_ON' });
  };
};

export const signIn = credentials => {
  return (dispatch, getState) => {
    axios
      .post(`http://localhost:8081/sw/tk`, {
        email: credentials.email,
        password: credentials.password
      })
      .then(res => {
        dispatch({ type: 'GET_TOKEN_SUCCESS', token: res.data });
        axios
          .post(`http://localhost:8081/sw/login`, {
            token: res.data
          })
          .then(resAuth => {
            console.log(resAuth);
            dispatch({ type: 'LOGIN_SUCCESS', auth: resAuth.data });
          })
          .catch(() => dispatch({ type: 'GET_TOKEN_ERROR' }));
      })
      .catch(() => dispatch({ type: 'GET_TOKEN_ERROR' }));
  };
};

export const signOut = () => {
  return (dispatch, getState) => {
    dispatch({ type: 'SIGNOUT_SUCCESS' });
  };
};

export const signUp = newUser => {
  return (dispatch, getState) => {
    axios
      .post(`http://localhost:8081/sw/nu`, newUser)
      .then(res => {
        dispatch({ type: 'SIGNUP_SUCCESS', token: res.data });
        axios
          .post(`http://localhost:8081/sw/login`, {
            token: res.data
          })
          .then(resAuth => {
            dispatch({ type: 'LOGIN_SUCCESS', auth: resAuth.data });
          })
          .catch(() => dispatch({ type: 'GET_TOKEN_ERROR' }));
      })
      .catch(() => dispatch({ type: 'SIGNUP_ERROR' }));
  };
};
