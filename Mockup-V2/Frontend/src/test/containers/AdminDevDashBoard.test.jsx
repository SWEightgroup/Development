import React, { Component } from 'react';
import { mount } from 'enzyme';
import { ProtectedRoute } from 'react-router-dom';
import AdminDevDashBoard from '../../view/containers/DashboardContainers/AdminDevDashBoard';

describe('auth.ROLE', () => {
  it('ROLE in AdminDevDashboard: Admin', () => {
    const user = {
      accountNonExpired: true,
      accountNonLocked: true,
      credentialsNonExpired: true,
      dateOfBirth: '2018-01-01T00:00:00.000+0000',
      enabled: true,
      firstName: 'Admin',
      id: '5cc59b407d6a042803148717',
      language: 'en',
      lastName: 'admin',
      role: 'ROLE_ADMIN',
      username: 'admin@admin.com'
    };
    const admin = {
      devList: [],
      usersList: []
    };
    // const fetchDeveloperListDispatch = ;

    const { language } = user;
    const { devList } = admin;

    const wrapperAdmin = mount(
      <ProtectedRoute
        path="/developers-management"
        isAllowed={user && user.role === 'ROLE_ADMIN'}
        component={AdminDevDashBoard}
      />
    );

    expect(wrapperAdmin);
  });
});
