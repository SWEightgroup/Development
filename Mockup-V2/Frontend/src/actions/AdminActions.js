import axios from 'axios';
import { store } from '../index';

export const fetchDeveloperList = () => {
  console.log('ENTRO IN FETCH');
  return dispatch => {
    axios
      .get('http://localhost:8081/users/get-all-development-to-enable', {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        dispatch({ type: 'UPDATE_USER_LIST', payload: res.data });
      })
      .catch(error => {
        //dispatch(signOut());
        console.log(error);
      });
  };
};
