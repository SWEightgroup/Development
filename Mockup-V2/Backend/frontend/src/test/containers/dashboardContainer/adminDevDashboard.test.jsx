import React from 'react';
import { ProtectedRoute } from 'react-router-dom';

import { mount } from 'enzyme';
import {
  mapStateToProps,
  mapDispatchToProps
} from AdminDevDashBoard from '../../../view/containers/DashboardContainers/AdminDevDashBoard';


import configureMockStore from 'redux-mock-store';
const mockStore = configureMockStore();

// CHECK IF MAPTOSTATEPROPRS WORS
// describe('Testing AdminDevDashBoard component', () => {
//     it('mapStateToProp WORKS??', () => {
//       const initialState = {
//         lastRolledNumber: 1
//       };
//       expect(mapStateToProps(initialState).lastRolledNumber).toEqual(1);
//     });
describe('Testing AdminDevDashBoard component', () => {
  it('check AdminDevDashBoard for each ROLE', () => {
    const authStudent = {
      user: {
        firstName: 'TestName',
        lastName: 'TestLastName',
        username: 'TestEmail',
        language: 'it',
        btAction: jest.fn(),
        role: 'ROLE_STUDENT',
        id: '12345456667'
      }
    };
    const authAdmin = {
      user: {
        firstName: 'TestName',
        lastName: 'TestLastName',
        username: 'TestEmail',
        language: 'it',
        btAction: jest.fn(),
        role: 'ROLE_ADMIN',
        id: '12345456667'
      }
    };

    const admin = {
      devList: {
        0: {
          id: '5cd0bfeeeb31459f700ae625',
          firstName: 'Maria',
          lastName: 'Masin',
          role: 'ROLE_DEVELOPER',
          language: 'it'
        },
        1: {
          id: '5cd0c003eb31459f700ae626',
          firstName: 'Giovannina',
          lastName: 'Basco',
          role: 'ROLE_DEVELOPER',
          language: 'it'
        },
        2: {
          id: '5cd0c017eb31459f700ae627',
          firstName: 'Paolo',
          lastName: 'Bacco',
          role: 'ROLE_DEVELOPER',
          language: 'it'
        }
      },

      filter: { reliability: 0, dateMin: '', dateMax: '', openFilters: false },
      loader: false,
      usersList: [],
      length: 0
    };

    const userAdmin = {
      accountNonExpired: true,
      accountNonLocked: true,
      authorities: { authority: 'ROLE_ADMIN' },
      credentialsNonExpired: true,
      currentGoal: 0,
      dateOfBirth: '2014-01-01T00:00:00.000+0000',
      enabled: true,
      favoriteTeacherIds: [],
      firstName: 'Pippo',
      id: '5ccf170f04dacb12740f6dc7',
      language: 'it',
      lastName: 'Rossi',
      role: 'ROLE_ADMIN',
      username: 'admin@colletta.it'
    };

    const wrapperAdmin = mount(
      <ProtectedRoute
        path="/developers-management"
        true
        component={AdminDevDashBoard}
      />
    );

    // componentWillUnmount()
    // expect(wrapper.find(AdminDevDashBoard).length).toBe(0);
    expect(wrapperAdmin.find(AdminDevDashBoard).length).toBe(1);
    // expect(wrapper.find(AdminDevDashBoard).length).toBe(0);
    // expect(wrapper.find(AdminDevDashBoard).length).toBe(0);
  });
});
