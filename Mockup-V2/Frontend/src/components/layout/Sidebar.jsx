import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';

class Sidebar extends Component {
  state = {};

  render() {
    return (
      <div className="scrollbar-sidebar">
        <div className="app-sidebar__inner">
          <ul className="vertical-nav-menu">
            <li className="app-sidebar__heading">Dashboards</li>
            <li>
              <NavLink to="/" className="nav-link mm-active">
                <i className="metismenu-icon pe-7s-pen" />
                Nuovo Esercizio
              </NavLink>
            </li>
          </ul>
        </div>
      </div>
    );
  }
}

export default Sidebar;
