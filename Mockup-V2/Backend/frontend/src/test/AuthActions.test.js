import configureMockStore from 'redux-mock-store';
import thunk from 'redux-thunk';
import axios from 'axios';
import expect from 'expect'; // You can use any testing library
import * as actions from '../actions/AuthActions';
import { _toastSuccess } from '../helpers/Utils';

const middlewares = [thunk];
const mockStore = configureMockStore(middlewares);

jest.mock('../helpers/Utils');

jest.mock('axios');

describe('SignIn', () => {
  it('creates FETCH_TODOS_SUCCESS when fetching todos has been done', () => {
    const res = {
      data: {
        id: '5cb9010804dacb417c1b9619',
        firstName: 'Peter',
        lastName: 'Pan',
        role: 'ROLE_ADMIN',
        language: 'en',
        dateOfBirth: 717897600000,
        enabled: true,
        accountNonExpired: true,
        authorities: [{ authority: 'ROLE_ADMIN' }],
        accountNonLocked: true,
        credentialsNonExpired: true,
        username: 'admin@colletta.it'
      },
      headers: {
        authorization:
          'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBjb2xsZXR0YS5pdCIsImV4cCI6MTU1NjkxOTA4NCwianRpIjoiNWNiOTAxMDgwNGRhY2I0MTdjMWI5NjE5In0.s4ve8g83lL4gqd_6FOa6oilbAvFjGBL1tKrZroPID0JKnfb3WXLqv_wTmzIxxrFy56G7MAXPnALOsgZIf2IiPA'
      }
    };
    axios.post.mockImplementationOnce(() => Promise.resolve(res));
    _toastSuccess.mockImplementationOnce(() => jest.fn());

    const userInfo = { user: res.data, token: res.headers.authorization };
    const expectedActions = [{ type: 'LOGIN_SUCCESS', userInfo }];
    const store = mockStore({});
    const credentials = { username: 'fakeuser@lol.it', password: 'fakwpwd' };
    const dispatchCall = async () => {
      await store.dispatch(actions.signIn(credentials));
      expect(store.getActions()).toEqual(expectedActions);
    };
    dispatchCall();

    expect(axios.post).toHaveBeenCalledTimes(1);
    expect(axios.post).toHaveBeenCalledWith('http://localhost:8081/login', {
      username: credentials.username,
      password: credentials.password
    });
  });
});
