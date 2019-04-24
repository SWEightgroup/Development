import configureMockStore from 'redux-mock-store';
import thunk from 'redux-thunk';
import mockAxios from 'axios';
import expect from 'expect'; // You can use any testing library
import * as actions from '../actions/AuthActions';

const middlewares = [thunk];
const mockStore = configureMockStore(middlewares);

jest.mock('react-toastify', () => ({
  toast: {
    configure: jest.fn()
  }
}));

describe('SignIn', () => {
  it('creates FETCH_TODOS_SUCCESS when fetching todos has been done', () => {
    const res = {
      data: {
        id: '5cb9010804dacb417c1b9619',
        firstName: 'Alfredo',
        lastName: 'Campanile',
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
    mockAxios.post.mockImplementationOnce(() => Promise.resolve(...res));

    const userInfo = { user: res.data, token: res.headers.authorization };
    const expectedActions = [{ type: 'LOGIN_SUCCESS', userInfo }];
    const store = mockStore({});
    const credentials = { username: 'fakeuser@lol.it', password: 'fakwpwd' };

    expect(mockAxios.get).toHaveBeenCalledTimes(1);
    expect(mockAxios.get).toHaveBeenCalledWith('http://localhost:8081/login', {
      username: credentials.username,
      password: credentials.password
    });

    store.dispatch(actions.signIn(credentials)).then(() => {
      expect(store.getActions()).toEqual(expectedActions);
    });
  });
});
