import axios from 'axios';
import { store } from '../index';

export const fetchDeveloperList = () => {
  axios
    .get('http://localhost:8081/users/get-all-development-to-enable', {
      headers: {
        Authorization: store.getState().auth.token
      }
    })
    .then(res => {
      store.dispatch({ type: 'UPDATE_USER_LIST', payload: res.data });
    })
    .catch(error => {
      console.log(error);
    });
};
