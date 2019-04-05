import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';

class DeveloperDashboard extends Component {
  downloadData = () => {
    console.log('Qui chiameremo la funzione di scaricamento');
  };

  render() {
    const { user } = this.props;
    const { language } = user;
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-center">
          <div className="py-5 text-center">
            <h2>{_translator('gen_devDashboard', language)}</h2>
            <p className="lead">
              {_translator('developerDashBoard_desc', language)}
            </p>
          </div>
        </div>
        <div className="row justify-content-center">
          <div className="col-md-11">
            <div className="card">
              <h5 className="card-header">
                {_translator('gen_devDashboard', language)}
              </h5>
              <div className="card-body">
                <p>{_translator('developerDashBoard_devDownText', language)}</p>
                <a
                  href="#"
                  onClick={this.downloadData}
                  className="btn btn-primary"
                >
                  {_translator('developerDashBoard_devDown', language)}
                </a>
              </div>
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

export default connect(mapStateToProps)(DeveloperDashboard);
