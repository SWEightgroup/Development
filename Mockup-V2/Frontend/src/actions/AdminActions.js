import axios from 'axios';
import { store } from '../index';

export const fetchDeveloperList = () => {
  axios
    .get('http://localhost:8081/users/developer/get-all-disabled', {
      headers: {
        Authorization: store.getState().auth.token
      }
    })
    .then(res => {
      store.dispatch({ type: 'UPDATE_DEV_LIST', payload: res.data });
    })
    .catch(error => {
      console.log(error);
    });
};

export const fetchUsersList = () => {
  axios
    .get('http://localhost:8081/users/admin/get-all-user', {
      headers: {
        Authorization: store.getState().auth.token
      }
    })
    .then(res => {
      store.dispatch({ type: 'UPDATE_USER_LIST', payload: res.data });
      console.log(res);
    })
    .catch(error => {
      console.log(error);
    });
};

export const deleteUser = id => {
  axios
    .delete('http://localhost:8081/admin/delete-user/' + id, {
      headers: {
        Authorization: store.getState().auth.token
      }
    })
    .then(res => {
      console.log(res);
      store.dispatch({ type: 'USER_DELETE_SUCCES', payload: { id: id } });
    })
    .catch(error => console.log(error));
};

export const activateUser = id => {
  axios
    .put(
      'http://localhost:8081/users/activate-user/' + id,
      {},
      {
        headers: {
          Authorization: store.getState().auth.token
        }
      }
    )
    .then(res => {
      console.log(res);
      store.dispatch({ type: 'DEV_APPROVE_SUCCES', payload: { id: id } });
    })
    .catch(error => console.log(error));
};
