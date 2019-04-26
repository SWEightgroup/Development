import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import _translator from '../../helpers/Translator';

class SidebarElementTeacher extends Component {
  state = {};

  render() {
    const { language } = this.props;
    return (
      <React.Fragment>
        <li>
          <a href="#root">
            <i className="metismenu-icon pe-7s-car" />
            {_translator('sidebarElementStudent_exercise', language)}
            <i className="metismenu-state-icon pe-7s-angle-down caret-left" />
          </a>
          <ul>
            <li>
              <NavLink to="/insert-exercise" activeClassName="mm-active">
                {_translator('sidebarElementTeacher_insertExercise', language)}
              </NavLink>
            </li>
            <li>
              <NavLink to="/doneHomework" activeClassName="mm-active">
                {_translator('sidebarElementTeacher_exercises', language)}
              </NavLink>
            </li>
            <li>
              <NavLink to="/class" activeClassName="mm-active">
                {_translator('sidebarElementTeacher_class', language)}
              </NavLink>
            </li>
          </ul>
        </li>
      </React.Fragment>
    );
  }
}

export default SidebarElementTeacher;
