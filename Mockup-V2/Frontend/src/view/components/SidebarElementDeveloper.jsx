import React, { Component } from 'react';
import _translator from '../../helpers/Translator';
import { NavLink } from 'react-router-dom';

class SidebarElementDeveloper extends Component {
  state = {};

  render() {
    const { language } = this.props;
    return (
      <React.Fragment>
        <li>
          <NavLink
            to="/developer-dashboard"
            className="nav-link"
            activeClassName="mm-active"
          >
            <i className="metismenu-icon pe-7s-cloud-download" />
            {_translator('gen_devDashboard', language)}
          </NavLink>
        </li>
      </React.Fragment>
    );
  }
}
export default SidebarElementDeveloper;
