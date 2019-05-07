import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import _translator from '../../helpers/Translator';

class SidebarElementAdministrator extends Component {
  state = {};

  render() {
    const { language, admin } = this.props;
    const { devList } = admin;
    console.log('AAAAAAAAAAADMINNNNNNNN');
    console.log(admin);
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
            {'  '}
            {devList && devList.length > 0 && (
              <span className="badge badge-pill badge-warning">
                {devList.length}
              </span>
            )}
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/users-management"
            className="nav-link"
            activeClassName="mm-active"
          >
            <i className="metismenu-icon pe-7s-users" />
            {_translator('gen_adminUsers', language)}
          </NavLink>
        </li>
      </React.Fragment>
    );
  }
}
export default SidebarElementAdministrator;
