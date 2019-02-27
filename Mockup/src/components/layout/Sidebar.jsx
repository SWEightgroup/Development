import React, { Component } from 'react';

class Sidebar extends Component {
  state = {};

  render() {
    return (
      <div className="scrollbar-sidebar">
        <div className="app-sidebar__inner">
          <ul className="vertical-nav-menu">
            <li className="app-sidebar__heading">Dashboards</li>
            <li>
              <a href="/" className="mm-active">
                <i className="metismenu-icon pe-7s-rocket" />
                Dashboard Example 1
              </a>
            </li>
          </ul>
        </div>
      </div>
    );
  }
}

export default Sidebar;
