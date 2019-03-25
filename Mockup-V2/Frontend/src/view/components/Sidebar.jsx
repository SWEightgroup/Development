import React, { Component } from 'react';
import { NavLink, withRouter } from 'react-router-dom';
import { _translator } from './Translator';

class Sidebar extends Component {
  render() {
    const { auth } = this.props;
    if (!(auth && auth.user)) return null;
    const userClass = 'student';
    const NavLinkstoRender = [];
    if (userClass === 'student')
      NavLinkstoRender.push(
        this.navMaker(
          '/dashboard',
          'pe-7s-user',
          _translator('gen_userDashboard')
        )
      );
    NavLinkstoRender.push(
      this.navMaker('/exercise', 'pe-7s-pen', _translator('gen_newExercise'))
    );
    return (
      <div className="scrollbar-sidebar">
        <div className="app-sidebar__inner">
          <ul className="vertical-nav-menu">
            <li className="app-sidebar__heading">
              {_translator('gen_dashboard')}
            </li>
            <li>
              {NavLinkstoRender.map((entry, index) => (
                <NavLink
                  to={entry.to}
                  className="nav-link"
                  activeClassName="mm-active"
                  key={index}
                >
                  <i className={`metismenu-icon ${entry.icon}`} />
                  {entry.label}
                </NavLink>
              ))}
            </li>
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
