import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';

class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const { firstName } = this.props.user.profile;
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-center">
          <div className="py-5 text-center">
            <h2>Pannello Utente</h2>
            <p className="lead">Ciao, {firstName}</p>
          </div>
        </div>
        <div className="row justify-content-center">
          <div className="col-md-11">
            <div className="card">
              <h5 className="card-header">Pannello Utente</h5>
              <div className="card-body">
                Pagina con varie carte, praticamente un riassunto delle varie
                pagine
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    user: state.auth.user
  };
};

export default connect(mapStateToProps)(Dashboard);
