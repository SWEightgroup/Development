import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { NavLink } from 'react-router-dom';
import { mount } from 'enzyme';
import Sidebar from '../../view/components/Sidebar';
import SidebarElementStudent from '../../view/components/SidebarElementStudent';
import SidebarElementAdministrator from '../../view/components/SidebarElementAdministrator';
import SidebarElementDeveloper from '../../view/components/SidebarElementDeveloper';
import SidebarElementTeacher from '../../view/components/SidebarElementTeacher';

describe('Testing Sidebar component', () => {
  it('check sidebar for student', () => {
    const auth = {
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
    const admin = {};
    const wrapper = mount(
      <BrowserRouter>
        <Sidebar auth={auth} admin={admin} />
      </BrowserRouter>
    );

    expect(wrapper.find(SidebarElementStudent).length).toBe(1);
    expect(wrapper.find(SidebarElementAdministrator).length).toBe(0);
    expect(wrapper.find(SidebarElementDeveloper).length).toBe(0);
    expect(wrapper.find(SidebarElementTeacher).length).toBe(0);
  });

  it('check sidebar for teacher', () => {
    const auth = {
      user: {
        firstName: 'TestName',
        lastName: 'TestLastName',
        username: 'TestEmail',
        language: 'it',
        btAction: jest.fn(),
        role: 'ROLE_TEACHER',
        id: '12345456667'
      }
    };
    const admin = {};
    const wrapper = mount(
      <BrowserRouter>
        <Sidebar auth={auth} admin={admin} />
      </BrowserRouter>
    );

    expect(wrapper.find(SidebarElementTeacher).length).toBe(1);
    expect(wrapper.find(SidebarElementAdministrator).length).toBe(0);
    expect(wrapper.find(SidebarElementStudent).length).toBe(0);
    expect(wrapper.find(SidebarElementDeveloper).length).toBe(0);
  });

  it('check sidebar for ADMIN', () => {
    const auth = {
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
      devList: {},
      filter: {
        dateMax: '',
        dateMin: '',
        openFilters: false,
        reliability: 0
      },
      loader: false,
      usersList: {
        accountNonExpired: true,
        accountNonLocked: true,
        authorities: { authority: 'ROLE_STUDENT' },
        credentialsNonExpired: true,
        currentGoal: -2,
        dateOfBirth: '1997-12-12T00:00:00.000+0000',
        enabled: true,
        favoriteTeacherIds: [],
        firstName: 'Claudio',
        id: '5ccda7a98f8ac93aa4aabd8e',
        language: 'it',
        lastName: 'Masin',
        role: 'ROLE_STUDENT',
        username: 'studente1@colletta.it'
      }
    };
    const wrapper = mount(
      <BrowserRouter>
        <Sidebar auth={auth} admin={admin} />
      </BrowserRouter>
    );

    expect(wrapper.find(SidebarElementAdministrator).length).toBe(1);
    expect(wrapper.find(SidebarElementStudent).length).toBe(0);
    expect(wrapper.find(SidebarElementDeveloper).length).toBe(0);
    expect(wrapper.find(SidebarElementTeacher).length).toBe(0);
  });

  it('check sidebar for DEVELOPER', () => {
    const auth = {
      user: {
        firstName: 'TestName',
        lastName: 'TestLastName',
        username: 'TestEmail',
        language: 'it',
        btAction: jest.fn(),
        role: 'ROLE_DEVELOPER',
        id: '12345456667'
      }
    };
    const wrapper = mount(
      <BrowserRouter>
        <Sidebar auth={auth} />
      </BrowserRouter>
    );

    expect(wrapper.find(SidebarElementDeveloper).length).toBe(1);
    expect(wrapper.find(SidebarElementAdministrator).length).toBe(0);
    expect(wrapper.find(SidebarElementStudent).length).toBe(0);
    expect(wrapper.find(SidebarElementTeacher).length).toBe(0);
  });
});
