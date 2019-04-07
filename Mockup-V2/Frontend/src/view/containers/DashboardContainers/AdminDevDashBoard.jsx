import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import DeveloperToAccept from '../../components/DeveloperToAccept';
import { fetchDeveloperList, deleteUser } from '../../../actions/AdminActions';

class AdminDevDashBoard extends Component {
  acceptDeveloper(isAccepted, usernameOrId) {
    console.log(isAccepted);
    console.log(usernameOrId);
    if (isAccepted) {
      console.log('YEAHHHHHHH');
    } else {
      deleteUser(usernameOrId);
    }
  }

  render() {
    const { user, admin } = this.props;
    const { language } = user;
    const { devList } = admin;

    const devRender =
      devList.length > 0 ? (
        devList.map(dev => (
          <DeveloperToAccept
            key={`dev-${dev.username}`}
            id={dev.id}
            firstName={dev.firstName}
            lastName={dev.lastName}
            username={dev.username}
            language={language}
            btAction={(isAccepted, username) =>
              this.acceptDeveloper(isAccepted, username)
            }
          />
        ))
      ) : (
        <td>{_translator('adminDevDashBoard_noDevApprove', language)}</td>
      );

    return (
      <React.Fragment>
        <div class="row">
          <div class="col-md-12">
            <div class="main-card mb-3 card">
              <div class="card-header">
                {_translator('SidebarElementAdministrator_devs', language)}
              </div>
              <div class="table-responsive">
                <table class="align-middle mb-0 table table-borderless table-striped table-hover">
                  <thead>
                    <tr>
                      <th>{_translator('gen_firstName', language)}</th>
                      <th class="text-center">
                        {_translator('gen_email', language)}
                      </th>
                      <th class="text-center">
                        {_translator('developerDashBoard_action', language)}
                      </th>
                    </tr>
                  </thead>
                  <tbody>{devRender}</tbody>
                </table>
              </div>
              <div class="d-block text-center card-footer">
                <button
                  onClick={() => fetchDeveloperList()}
                  class="btn-wide btn btn-primary"
                >
                  {_translator('developerDashBoard_devDown', language)}
                </button>
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

const mapStateToProps = store => {
  return {
    user: store.auth.user,
    admin: store.admin
  };
};

export default connect(mapStateToProps)(AdminDevDashBoard);
