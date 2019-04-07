import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import User from '../../components/User';
import { fetchUsersList, deleteUser } from '../../../actions/AdminActions';

class AdminDevDashBoard extends Component {
  render() {
    const { user, admin } = this.props;
    const { language } = user;
    const { usersList } = admin;
    console.log(usersList);

    const devRender =
      usersList.length > 0 ? (
        usersList.map(user => (
          <User
            key={`user-${user.username}`}
            id={user.id}
            firstName={user.firstName}
            lastName={user.lastName}
            username={user.username}
            role={user.role}
            language={language}
            btAction={id => deleteUser(id)}
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
                  onClick={() => fetchUsersList()}
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

export default connect(mapStateToProps)(AdminDevDashBoard);
