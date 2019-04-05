import React, { Component } from 'react';
import _translator from '../../helpers/Translator';
import { NavLink } from 'react-router-dom';

class SidebarElementAdministrator extends Component {
  state = {};

  render() {
    const { language } = this.props;
    return (
      <React.Fragment>
        <li>
          <NavLink
            to="/developers-management"
            className="nav-link"
            activeClassName="mm-active"
          >
            <i className="metismenu-icon pe-7s-plugin" />
            {_translator('SidebarElementAdministrator_devs', language)}
          </NavLink>
        </li>
      </React.Fragment>
    );
  }
}
export default SidebarElementAdministrator;
