import axios from 'axios';
import { store } from '../index';

export const fetchDeveloperList = () => {
  return dispatch => {
    axios
      .get('http://localhost:8081/users/developer/get-all-disabled', {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        dispatch({ type: 'UPDATE_DEV_LIST', payload: res.data });
      })
      .catch(error => {
        console.log(error);
      });
  };
};

export const fetchUsersList = () => {
  return dispatch => {
    axios
      .get('http://localhost:8081/users/admin/get-all-user', {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        dispatch({ type: 'UPDATE_USER_LIST', payload: res.data });
        console.log(res);
      })
      .catch(error => {
        console.log(error);
      });
  };
};

export const deleteUser = ({ usernameOrId }) => {
  return dispatch => {
    axios
      .delete(`http://localhost:8081/admin/delete-user/${usernameOrId}`, {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        console.log(res);
        dispatch({ type: 'USER_DELETE_SUCCES', payload: { usernameOrId } });
        console.log('ppppppppppp');
      })
      .catch(error => console.log(error));
  };
};

export const activateUser = ({ usernameOrId }) => {
  return dispatch => {
    axios
      .put(
        `http://localhost:8081/users/activate-user/${usernameOrId}`,
        {},
        {
          headers: {
            Authorization: store.getState().auth.token
          }
        }
      )
      .then(res => {
        console.log(res);
        dispatch({ type: 'DEV_APPROVE_SUCCES', payload: { usernameOrId } });
        console.log('ppppppppppp');
      })
      .catch(error => console.log(error));
  };
};
