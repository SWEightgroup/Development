import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';

class Sidebar extends Component {
  render() {
    return (
      <div className="scrollbar-sidebar">
        <div className="app-sidebar__inner">
          <ul className="vertical-nav-menu">
            <li className="app-sidebar__heading">Dashboards</li>
            <li>
              <NavLink
                to="/exercise"
                className="nav-link"
                activeClassName="mm-active"
              >
                <i className="metismenu-icon pe-7s-pen" />
                Nuovo Esercizio
              </NavLink>
              <NavLink
                to="/dashboard"
                className="nav-link"
                activeClassName="mm-active"
              >
                <i className="metismenu-icon pe-7s-user" />
                Pannello Utente
              </NavLink>
            </li>
          </ul>
        </div>
      </div>
    );
  }
}

export default Sidebar;
