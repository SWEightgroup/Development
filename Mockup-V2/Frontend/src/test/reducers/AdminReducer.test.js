import thunk from 'redux-thunk';
import axios from 'axios';
import AdminReducer from '../../reducers/AdminReducer';
import * as actions from '../../actions/AuthActions';

jest.mock('axios');

describe('authenticate reducer', () => {
  const initState = {
    devList: [],
    usersList: []
  };
  const fakeStateDev1User1 = {
    devList: {
      accountNonExpired: true,
      accountNonLocked: true,
      authorities: 'ROLE_DEVELOPER',
      credentialsNonExpired: true,
      dateOfBirth: '1999-12-31T00:00:00.000+0000',
      enabled: true,
      firstName: 'Cane',
      id: '5ccbf41e476bb2463c6ecf24',
      language: 'it',
      lastName: 'Gatto',
      role: 'ROLE_DEVELOPER',
      username: 'dev@colletta.com'
    },
    loader: false,
    usersList: {
      accountNonExpired: true,
      accountNonLocked: true,
      authorities: 'ROLE_DEVELOPER',
      credentialsNonExpired: true,
      dateOfBirth: '1999-12-31T00:00:00.000+0000',
      enabled: true,
      firstName: 'Cane',
      id: '5ccbf41e476bb2463c6ecf24',
      language: 'it',
      lastName: 'Gatto',
      role: 'ROLE_DEVELOPER',
      username: 'dev@colletta.com'
    }
  };
  const fakeStateDev1User0 = {
    devList: {
      accountNonExpired: true,
      accountNonLocked: true,
      authorities: 'ROLE_DEVELOPER',
      credentialsNonExpired: true,
      dateOfBirth: '1999-12-31T00:00:00.000+0000',
      enabled: true,
      firstName: 'Cane',
      id: '5ccbf41e476bb2463c6ecf24',
      language: 'it',
      lastName: 'Gatto',
      role: 'ROLE_DEVELOPER',
      username: 'dev@colletta.com'
    },
    loader: false,
    userList: []
  };
  const fakeStateDev0User1 = {
    devList: [],
    loader: false,
    userList: {
      accountNonExpired: true,
      accountNonLocked: true,
      authorities: 'ROLE_DEVELOPER',
      credentialsNonExpired: true,
      dateOfBirth: '1999-12-31T00:00:00.000+0000',
      enabled: true,
      firstName: 'Cane',
      id: '5ccbf41e476bb2463c6ecf24',
      language: 'it',
      lastName: 'Gatto',
      role: 'ROLE_DEVELOPER',
      username: 'dev@colletta.com'
    }
  };

  const res = {
    data: {
      accountNonExpired: true,
      accountNonLocked: true,
      authorities: 'ROLE_DEVELOPER',
      credentialsNonExpired: true,
      dateOfBirth: '1999-12-31T00:00:00.000+0000',
      enabled: true,
      firstName: 'Cane',
      id: '5ccbf41e476bb2463c6ecf24',
      language: 'it',
      lastName: 'Gatto',
      role: 'ROLE_DEVELOPER',
      username: 'dev@colletta.com'
    }
  };
  // DA CAPIRE QUAL' E' IL FLAG CHE MI INDICA SE IL DEVELOPER E' APPROVATO O MENO
  //   it('AdminReducers UPDATE_DEV_LIST', () => {
  //     axios.post.mockImplementationOnce(() => Promise.resolve(res));
  //     const expectedActions = [{ type: 'UPDATE_DEV_LIST', payload: res.data }];
  //     expect(AdminReducer(fakeStateDev1User1, expectedActions)).toEqual({
  //       ...fakeStateDev1User1,
  //       devList: res.data
  //     });
  //   });

  //   it('AdminReducers UPDATE_USER_LIST', () => {
  //     axios.post.mockImplementationOnce(() => Promise.resolve(res));
  //     const expectedActions = [{ type: 'UPDATE_USER_LIST', payload: res.data }];
  //     expect(AdminReducer(fakeStateDev1User0, expectedActions)).toEqual({
  //       ...fakeStateDev1User0,
  //       devList: res.data
  //     });
  //   });

  it('AdminReducers USER_DELETE_SUCCES', () => {
    axios.delete.mockImplementationOnce(() => Promise.resolve(res));
    const expectedActions = [
      { type: 'USER_DELETE_SUCCES', payload: { usernameOrId } }
    ];
    expect(AdminReducer(fakeStateDev1User0, expectedActions)).toEqual({
      ...fakeStateDev1User0,
      devList: res.data
    });
  });
});
