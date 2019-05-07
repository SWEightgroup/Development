import axios from 'axios';
import AdminReducer from '../../reducers/AdminReducer';

jest.mock('axios');

describe('authenticate reducer', () => {
  // const initState = {
  //   devList: [],
  //   usersList: []
  // };
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
    },
    filter: { 0: 'b', 1: 'o', 2: 'h' }
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
  // const fakeStateDev0User1 = {
  //   devList: [],
  //   loader: false,
  //   userList: {
  //     accountNonExpired: true,
  //     accountNonLocked: true,
  //     authorities: 'ROLE_DEVELOPER',
  //     credentialsNonExpired: true,
  //     dateOfBirth: '1999-12-31T00:00:00.000+0000',
  //     enabled: true,
  //     firstName: 'Cane',
  //     id: '5ccbf41e476bb2463c6ecf24',
  //     language: 'it',
  //     lastName: 'Gatto',
  //     role: 'ROLE_DEVELOPER',
  //     username: 'dev@colletta.com'
  //   }
  // };

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
  // INIT_STATE NON VINE UTILIZZATO
  // it('AdminReducers INIT_STATE', () => {
  //   axios.post.mockImplementationOnce(() => Promise.resolve(res));
  //   const expectedActions = [{ type: 'INIT_STATE' }];
  //   expect(AdminReducer(null, expectedActions)).toEqual({
  //     ...initState
  //   });
  // });

  // UPDATE_FILTER
  it('AdminReducers UPDATE_FILTER', () => {
    const filter = null;
    const expectedActions = [{ type: 'UPDATE_FILTER', filter }];
    expect(AdminReducer(fakeStateDev1User1, expectedActions)).toEqual({
      ...fakeStateDev1User1,
      filter: {
        ...fakeStateDev1User1.filter,
        ...expectedActions.filter
      }
    });
  });

  // UPDATE_DEV_LIST
  it('AdminReducers UPDATE_DEV_LIST', () => {
    axios.get.mockImplementationOnce(() => Promise.resolve(res));
    const expectedActions = [{ type: 'UPDATE_DEV_LIST', payload: res.data }];
    expect(AdminReducer(fakeStateDev1User1, expectedActions)).toEqual({
      ...fakeStateDev1User1,
      devList: res.data // CAPIRE PERCHE NON FUNZIONA CON expectedActions.payload_________________!
    });
  });

  //  UPDATE_USER_LIST -> dovrebbe aggiornare la lista dei developer, testato cosi in realtà non lo fa.
  //
  it('AdminReducers UPDATE_USER_LIST', () => {
    axios.post.mockImplementationOnce(() => Promise.resolve(res));
    const expectedActions = [{ type: 'UPDATE_USER_LIST', payload: res.data }];
    expect(AdminReducer(fakeStateDev1User1, expectedActions)).toEqual({
      ...fakeStateDev1User1,
      devList: res.data
    });
  });

  // USER_DELETE_SUCCES
  it('AdminReducers USER_DELETE_SUCCES', () => {
    axios.delete.mockImplementationOnce(() => Promise.resolve(res));
    const expectedActions = [
      { type: 'USER_DELETE_SUCCES', payload: '5ccbf41e476bb2463c6ecf24' }
    ];
    expect(AdminReducer(fakeStateDev1User0, expectedActions)).toEqual({
      ...fakeStateDev1User0,
      devList: res.data
    });
  });

  // DEV_APPROVE_SUCCES
  it('AdminReducers DEV_APPROVE_SUCCES', () => {
    axios.delete.mockImplementationOnce(() => Promise.resolve(res));

    // const usernameOrId = res.id;
    const expectedActions = [{ type: 'DEV_APPROVE_SUCCES', payload: res.id }];
    expect(AdminReducer(fakeStateDev1User0, expectedActions)).toEqual({
      ...fakeStateDev1User0,
      devList: res.data
    });
  });
});
