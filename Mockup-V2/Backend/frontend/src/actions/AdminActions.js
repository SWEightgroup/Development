import axios from 'axios';
import { saveAs } from 'file-saver';
import { store } from '../index';
import _translator from '../helpers/Translator';
import { _toastSuccess, _toastError } from '../helpers/Utils';

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
      })
      .catch(error => {
        console.error(error);
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
        dispatch({ type: 'USER_DELETE_SUCCES', payload: { usernameOrId } });
      })
      .catch(error => console.error(error));
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
        dispatch({ type: 'DEV_APPROVE_SUCCES', payload: { usernameOrId } });
      })
      .catch(error => console.error(error));
  };
};

export const downlaodAll = () => {
  return dispatch => {
    axios
      .get(`http://localhost:8081/phrases/downloadAll`, {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        _toastSuccess(
          _translator(
            'developer_downloadind',
            store.getState().auth.user.language
          )
        );
        const blob = new Blob([JSON.stringify(res.data)], {
          type: 'text/plain;charset=utf-8'
        });
        saveAs(blob, 'PhrasesList.json');
      })
      .catch(error =>
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        )
      );
  };
};
