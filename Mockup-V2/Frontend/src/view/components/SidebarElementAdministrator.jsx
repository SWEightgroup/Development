import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { NavLink } from 'react-router-dom';
import _translator from '../../helpers/Translator';
class SidebarElementAdministrator extends Component {
  state = {};
  render() {
    const { language } = this.props;
    return (
      <React.Fragment>
        <li>
          <a href="#root">
            <i className="metismenu-icon pe-7s-pen" />
            {_translator('SidebarElementAdministrator_users', language)}
          </a>
        </li>
      </React.Fragment>
    );
  }
}
export default SidebarElementAdministrator;
