import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import _translator from './Translator';
class SidebarElementStudent extends Component {
  state = {};
  render() {
    return (
      <React.Fragment>
        <li>
          <a href="#root">
            <i className="metismenu-icon pe-7s-pen" />
            {_translator('sidebarElementStudent_exercise')}
            <i className="metismenu-state-icon pe-7s-angle-down caret-left" />
          </a>
          <ul>
            <li>
              <NavLink to="/exercise" activeClassName="mm-active">
                {_translator('sidebarElementStudent_freeExercise')}
              </NavLink>
            </li>
            <li>
              <NavLink to="/homework" activeClassName="mm-active">
                {_translator('sidebarElementStudent_exercises')}
              </NavLink>
            </li>
            <li>
              <NavLink to="/grades" activeClassName="mm-active">
                {_translator('sidebarElementStudent_marks')}
              </NavLink>
            </li>
          </ul>
        </li>
      </React.Fragment>
    );
  }
}

export default SidebarElementStudent;
