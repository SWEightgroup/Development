import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import User from '../../components/User';
import { fetchUsersList, deleteUser } from '../../../actions/AdminActions';
import { _confirmAlert } from '../../../helpers/Utils';

class AdminDevDashBoard extends Component {
  state = {};

  constructor(props) {
    super(props);
    props.fetchUsersListDispatch();
  }

  render() {
    const {
      user,
      admin,
      fetchUsersListDispatch,
      deleteUserDispatch
    } = this.props;
    const { language } = user;
    const { usersList } = admin;

    const devRender =
      usersList.length > 0 ? (
        usersList.map(_user => (
          <User
            key={`user-${_user.username}`}
            id={_user.id}
            firstName={_user.firstName}
            lastName={_user.lastName}
            username={_user.username}
            role={_user.role}
            language={language}
            btAction={usernameOrId =>
              _confirmAlert(
                {
                  title: 'Attenzione',
                  message: 'Sei sicuro di voler Eliminare?'
                },
                deleteUserDispatch,
                { usernameOrId }
              )
            }
          />
        ))
      ) : (
        <tr>
          <td>{_translator('adminDevDashBoard_noUser', language)}</td>
          <td />
          <td />
          <td />
        </tr>
      );

    return (
      <React.Fragment>
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
                        {_translator('gen_role', language)}
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
                  onClick={() => fetchUsersListDispatch()}
                  className="btn btn-wide btn-primary mt-2"
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

const mapDispatchToProps = dispatch => {
  return {
    fetchUsersListDispatch: () => dispatch(fetchUsersList()),
    deleteUserDispatch: user => dispatch(deleteUser(user))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdminDevDashBoard);
