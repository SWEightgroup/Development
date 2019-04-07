import React, { Component } from 'react';
import _translator from '../../helpers/Translator';

export default class User extends Component {
  render() {
    const {
      firstName,
      lastName,
      username,
      language,
      btAction,
      role,
      id
    } = this.props;
    let textRole = '';
    switch (role) {
      case 'ROLE_ADMINISTRATOR':
        textRole = _translator('gen_admin', language);
        break;
      case 'ROLE_DEVELOPER':
        textRole = _translator('gen_developer', language);
        break;
      case 'ROLE_STUDENT':
        textRole = _translator('gen_student', language);
        break;
      case 'ROLE_TEACHER':
        textRole = _translator('gen_teacher', language);
        break;
      default:
        textRole = '????';
        break;
    }
    return (
      <tr>
        <td>
          <div class="widget-content p-0">
            <div class="widget-content-wrapper">
              <div class="widget-content-left mr-3">
                <div class="widget-content-left">
                  <img
                    width="40"
                    class="rounded-circle"
                    src="assets/images/avatars/4.jpg"
                    alt=""
                  />
                </div>
              </div>
              <div class="widget-content-left flex2">
                <div class="widget-heading">{firstName + ' ' + lastName}</div>
              </div>
            </div>
          </div>
        </td>
        <td class="text-center">{username}</td>
        <td class="text-center">{textRole}</td>
        <td class="text-center">
          <div className="btn-group" role="group" aria-label="Basic example">
            <button
              type="button"
              className="btn btn-danger"
              onClick={() => btAction(id)}
            >
              {_translator('adminUserDashBoard_delete', language)}
            </button>
          </div>
        </td>
      </tr>
    );
  }
}
