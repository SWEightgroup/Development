import React, { Component } from 'react';
import _translator from '../../helpers/Translator';

export default class DeveloperToAccept extends Component {
  render() {
    const {
      firstName,
      lastName,
      username,
      language,
      btAction,
      id
    } = this.props;
    return (
      <tr>
        <td>
          <div className="widget-content p-0">
            <div className="widget-content-wrapper">
              <div className="widget-content-left mr-3">
                <div className="widget-content-left">
                  <img
                    width="40"
                    className="rounded-circle"
                    src="assets/images/avatars/4.jpg"
                    alt=""
                  />
                </div>
              </div>
              <div className="widget-content-left flex2">
                <div className="widget-heading">
                  {firstName + ' ' + lastName}
                </div>
              </div>
            </div>
          </div>
        </td>
        <td className="text-center">{username}</td>
        <td className="text-center">
          <div className="btn-group" role="group" aria-label="Basic example">
            <button
              type="button"
              className="btn btn-success"
              onClick={() => btAction(true, id)}
            >
              {_translator('developerToAccept_allow', language)}
            </button>
            <button
              type="button"
              className="btn btn-danger"
              onClick={() => btAction(false, id)}
            >
              {_translator('developerToAccept_deny', language)}
            </button>
          </div>
        </td>
      </tr>
    );
  }
}
