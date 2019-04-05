import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import DeveloperToAccept from '../../components/DeveloperToAccept';
import { fetchDeveloperList } from '../../../actions/AdminActions';

class AdminDevDashBoard extends Component {
  acceptDeveloper(isAccepted, username) {
    console.log(isAccepted);
    console.log(username);
  }

  render() {
    const { user, admin } = this.props;
    const { language } = user;
    //const { devList } = admin;

    const devList = [
      {
        firstName: 'Sebastiano',
        lastName: 'Caccaro',
        username: 'sebastianocaccaro@gmail.com'
      },
      {
        firstName: 'Damien',
        lastName: 'Ciagola',
        username: 'damien.ciagola@gmail.com'
      },
      {
        firstName: 'Sebastiano',
        lastName: 'Caccaro',
        username: 'sebastianocaccaro@gmail.cm'
      },
      {
        firstName: 'Sebastiano',
        lastName: 'Caccaro',
        username: 'sebastianocaccaro@gmail.co'
      },
      {
        firstName: 'Sebastiano',
        lastName: 'Caccaro',
        username: 'sebastianocaccaro@gmail.c'
      }
    ];

    const devRender =
      devList.length > 0
        ? devList.map(dev => (
            <DeveloperToAccept
              key={'dev-' + dev.username}
              firstName={dev.firstName}
              lastName={dev.lastName}
              username={dev.username}
              language={language}
              btAction={(isAccepted, username) =>
                this.acceptDeveloper(isAccepted, username)
              }
            />
          ))
        : 'Nessun developer da approvare';

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
              <h5 className="card-title">Developer to Approve</h5>
              <ul className="list-group">{devRender}</ul>
              <a
                href="#"
                onClick={fetchDeveloperList}
                className="btn btn-primary mt-2"
              >
                {_translator('developerDashBoard_devDown', language)}
              </a>
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
