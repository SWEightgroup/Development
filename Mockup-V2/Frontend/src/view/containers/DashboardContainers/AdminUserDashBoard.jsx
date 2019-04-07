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
      usersList.length > 0
        ? usersList.map(user => (
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
        : _translator('adminDevDashBoard_noUser', language);

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
                        {_translator('gen_role', language)}
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

/*       <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-center">
          <div className="py-5 text-center">
            <h2>{_translator('gen_adminUsers', language)}</h2>
            <p className="lead">
              {_translator('adminUserDashBoard_desc', language)}
            </p>
          </div>
        </div>
        <div className="row justify-content-center">
          <div className="main-card mb-3 card col-lg-8 col-md-10 col-sm-11">
            <div className="card-body">
              <h5 className="card-title">
                {_translator('gen_adminUsers', language)}
              </h5>
              <ul className="list-group">{devRender}</ul>
              <button
                type="button"
                onClick={() => fetchUsersList()}
                className="btn btn-primary mt-2"
              >
                {_translator('developerDashBoard_devDown', language)}
              </button>
            </div>
          </div>
        </div>
      </div> */
