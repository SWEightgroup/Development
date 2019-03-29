import React, { Component } from 'react';
import { NavLink, withRouter } from 'react-router-dom';
import _translator from './Translator';
import SidebarElementStudent from './SidebarElementStudent';
import SidebarElementTeacher from './SidebarElementTeacher';
import SidebarElementAdministrator from './SidebarElementAdministrator';
class Sidebar extends Component {
  render() {
    const { auth } = this.props;
    const { user } = auth;
    const { role } = user;
    if (!(auth && auth.user)) return null;
    let roleSpecificNav = null;

    if (role === 'ROLE_STUDENT') {
      roleSpecificNav = <SidebarElementStudent />;
    }
    if (role === 'ROLE_ADMIN') {
      roleSpecificNav = <SidebarElementAdministrator />;
    }

    return (
      <div className="scrollbar-sidebar">
        <div className="app-sidebar__inner">
          <ul className="vertical-nav-menu">
            <li className="app-sidebar__heading">
              {_translator('gen_dashboard')}
            </li>
            <li>
              <NavLink
                to={'/dashboard'}
                className="nav-link"
                activeClassName="mm-active"
              >
                <i className={'metismenu-icon pe-7s-user'} />
                {_translator('gen_userDashboard')}
              </NavLink>
            </li>
            {roleSpecificNav}
          </ul>
        </div>
      </div>
    );
  }

  navMaker(to, icon, label) {
    return {
      to,
      icon,
      label
    };
  }
}

export default withRouter(Sidebar);
