import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import DeveloperToAccept from '../../components/DeveloperToAccept';
import {
  fetchDeveloperList,
  deleteUser,
  activateUser
} from '../../../actions/AdminActions';
import { _confirmAlert } from '../../../helpers/Utils';

class AdminDevDashBoard extends Component {
  constructor(props) {
    super(props);
    props.fetchDeveloperListDispatch();
  }

  acceptDeveloper(isAccepted, usernameOrId) {
    const { activateUserDispatch, deleteUserDispatch } = this.props;

    if (isAccepted) {
      _confirmAlert(
        { message: 'Sei sicuro di voler approvare?' },
        activateUserDispatch,
        { usernameOrId }
      );
    } else {
      _confirmAlert(
        { message: 'Sei sicuro di voler eliminare?' },
        deleteUserDispatch,
        { usernameOrId }
      );
    }
  }

  render() {
    const { user, admin, fetchDeveloperListDispatch } = this.props;
    const { language } = user;
    const { devList } = admin;
    console.log('tutti');
    console.log('user ', user);
    console.log('admin', admin);
    console.log('fetchdevList', fetchDeveloperList);
    console.log('language', language);
    console.log('devlist', devList);
    console.log('prop', this.props);

    const devRender =
      devList && devList.length > 0 ? (
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
        <tr>
          <td>{_translator('adminDevDashBoard_noDevApprove', language)}</td>
          <td />
          <td />
        </tr>
      );

    return (
      <div className="row">
        <div className="col-md-12">
          <div className="main-card mb-3 card">
            <div className="card-header">
              {_translator('SidebarElementAdministrator_devs', language)}
            </div>
            <div className="table-responsive">
              <table className="align-middle mb-0 table table-borderless table-striped table-hover">
                <thead>
                  <tr>
                    <th>{_translator('gen_firstName', language)}</th>
                    <th className="text-center">
                      {_translator('gen_email', language)}
                    </th>
                    <th className="text-center">
                      {_translator('developerDashBoard_action', language)}
                    </th>
                  </tr>
                </thead>
                <tbody>{devRender}</tbody>
              </table>
            </div>
            <div className="d-block text-center card-footer">
              <button
                type="button"
                onClick={() => fetchDeveloperListDispatch()}
                className="btn-wide btn btn-primary"
              >
                {_translator('adminDashBoard_update', language)}
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = store => {
  return {
    user: store.auth.user,
    admin: store.admin
  };
};

const mapDispatchToProps = dispatch => {
  return {
    fetchDeveloperListDispatch: () => dispatch(fetchDeveloperList()),
    deleteUserDispatch: user => dispatch(deleteUser(user)),
    activateUserDispatch: user => dispatch(activateUser(user))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdminDevDashBoard);
