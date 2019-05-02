import React, { Component } from 'react';
import { NavLink, withRouter } from 'react-router-dom';
import _translator from '../../helpers/Translator';
import SidebarElementStudent from './SidebarElementStudent';
import SidebarElementTeacher from './SidebarElementTeacher';
import SidebarElementAdministrator from './SidebarElementAdministrator';
import SidebarElementDeveloper from './SidebarElementDeveloper';

class Sidebar extends Component {
  state = {};

  render() {
    const { auth, admin } = this.props;
    const { user } = auth;
    const { role, language } = user;
    if (!(auth && auth.user)) return null;
    let roleSpecificNav = null;

    if (role === 'ROLE_STUDENT') {
      roleSpecificNav = <SidebarElementStudent language={language} />;
    } else if (role === 'ROLE_ADMIN') {
      roleSpecificNav = (
        <SidebarElementAdministrator language={language} admin={admin} />
      );
    } else if (role === 'ROLE_TEACHER') {
      roleSpecificNav = <SidebarElementTeacher language={language} />;
    } else if (role === 'ROLE_DEVELOPER') {
      roleSpecificNav = <SidebarElementDeveloper language={language} />;
    }
    return (
      <div className="scrollbar-sidebar">
        <div className="app-sidebar__inner">
          <ul className="vertical-nav-menu">
            <li className="app-sidebar__heading">
              {_translator('gen_dashboard', language)}
            </li>
            <li>
              <NavLink
                to="/dashboard"
                className="nav-link"
                activeClassName="mm-active"
              >
                <i className="metismenu-icon pe-7s-user" />
                {_translator('gen_userDashboard', language)}
              </NavLink>
            </li>
            {roleSpecificNav}
          </ul>
        </div>
      </div>
    );
  }
}

export default withRouter(Sidebar);
