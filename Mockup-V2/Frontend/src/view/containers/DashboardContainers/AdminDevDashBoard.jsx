import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import DeveloperToAccept from '../../components/DeveloperToAccept';

class AdminDevDashBoard extends Component {
  acceptDeveloper(isAccepted, username) {
    console.log(isAccepted);
    console.log(username);
  }

  render() {
    const { user } = this.props;
    const { language } = user;

    const sampleUsers = [
      {
        firstName: 'Sebastiano',
        lastName: 'Caccaro',
        username: 'sebastianocaccaro@gmail.com'
      },
      {
        firstName: 'Sebastiano',
        lastName: 'Caccaro',
        username: 'sebastianocaccaro@gmail.com'
      },
      {
        firstName: 'Sebastiano',
        lastName: 'Caccaro',
        username: 'sebastianocaccaro@gmail.com'
      },
      {
        firstName: 'Sebastiano',
        lastName: 'Caccaro',
        username: 'sebastianocaccaro@gmail.com'
      },
      {
        firstName: 'Sebastiano',
        lastName: 'Caccaro',
        username: 'sebastianocaccaro@gmail.com'
      }
    ];
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-center">
          <div className="py-5 text-center">
            <h2>{_translator('SidebarElementAdministrator_devs', language)}</h2>
            <p className="lead">
              Approve or deny developers' subscription requests
            </p>
          </div>
        </div>
        <div className="row justify-content-center">
          <div className="main-card mb-3 card col-lg-8 col-md-10 col-sm-11">
            <div className="card-body">
              <h5 className="card-title">List group custom content</h5>
              <ul className="list-group">
                {sampleUsers.map(user => (
                  <DeveloperToAccept
                    firstName={user.firstName}
                    lastName={user.lastName}
                    username={user.username}
                    language={language}
                    btAction={(isAccepted, username) =>
                      this.acceptDeveloper(isAccepted, username)
                    }
                  />
                ))}
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = store => {
  return {
    user: store.auth.user
  };
};

export default connect(mapStateToProps)(AdminDevDashBoard);
