import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import DeveloperToAccept from '../../components/DeveloperToAccept';
import { fetchUsersList } from '../../../actions/AdminActions';

class AdminDevDashBoard extends Component {
  acceptDeveloper(isAccepted, username) {
    console.log(isAccepted);
    console.log(username);
  }

  render() {
    const { user, admin } = this.props;
    const { language } = user;
    const { usersList } = admin;

    const devRender =
      usersList.length > 0
        ? usersList.map(dev => (
            <DeveloperToAccept
              key={`dev-${dev.username}`}
              firstName={dev.firstName}
              lastName={dev.lastName}
              username={dev.username}
              language={language}
              btAction={(isAccepted, username) =>
                this.acceptDeveloper(isAccepted, username)
              }
            />
          ))
        : _translator('adminDevDashBoard_noUser', language);

    return (
      <div className="app-main__inner full-height-mobile">
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

export default connect(mapStateToProps)(AdminDevDashBoard);
