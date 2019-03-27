import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { _translator } from './Translator';
class SidebarElementStudent extends Component {
  state = {};
  render() {
    return (
      <React.Fragment>
        <li>
          <a href="#root">
            <i className="metismenu-icon pe-7s-car" />
            {_translator('sidebarElementStudent_exercise')}
            <i className="metismenu-state-icon pe-7s-angle-down caret-left" />
          </a>
          <ul>
            <li>
              <NavLink to="/acaso" activeClassName="mm-active">
                {_translator('sidebarElementTeacher_insertExercise')}
              </NavLink>
            </li>
            <li>
              <NavLink to="/acaso" activeClassName="mm-active">
                {_translator('sidebarElementTeacher_exercises')}
              </NavLink>
            </li>
            <li>
              <NavLink to="/acaso" activeClassName="mm-active">
                {_translator('sidebarElementTeacher_ExerciseDone')}
              </NavLink>
            </li>
          </ul>
        </li>
      </React.Fragment>
    );
  }
}

export default SidebarElementStudent;
