import React, { Component } from 'react';
import _translator from '../../helpers/Translator';

export default class DeveloperToAccept extends Component {
  render() {
    const { firstName, lastName, username, language, btAction } = this.props;
    return (
      <li className="list-group-item d-flex justify-content-between align-items-center">
        {username}
        <br />
        {firstName + ' ' + lastName}
        <div className="btn-group" role="group" aria-label="Basic example">
          <button
            type="button"
            className="btn btn-success"
            onClick={() => btAction(true, username)}
          >
            {_translator('developerToAccept_allow', language)}
          </button>
          <button
            type="button"
            className="btn btn-danger"
            onClick={() => btAction(false, username)}
          >
            {_translator('developerToAccept_deny', language)}
          </button>
        </div>
      </li>
    );
  }
}
