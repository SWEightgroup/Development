import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import SignedInLinks from './SignedInLinks';
import SignedOutLinks from './SignedOutLinks';
import 'materialize-css/dist/css/materialize.min.css';
import 'jquery/dist/jquery.slim';

import M from 'materialize-css/dist/js/materialize.min.js';

class Navbar extends Component {
  componentDidMount() {
    M.Sidenav.init(this.sidenav, { edge: 'right' });
  }

  render() {
    const { auth, profile } = this.props;
    const links = auth.uid ? <SignedInLinks profile={profile} navBar={this.sidenav} /> : <SignedOutLinks />;
    return (
      <React.Fragment>
        <div className="navbar-fixed">
          <nav>
            <div className="nav-wrapper grey darken-3">
              <div className="container">
                <Link to="/" className="brand-logo">
                  RQConnect
                </Link>
                <ul
                  className="right hide-on-med-and-down"
                  ref={sidenav => {
                    this.sidenav = sidenav;
                  }}
                >
                  {links}
                </ul>
              </div>
              <a data-target="slide-out" className="right sidenav-trigger pointer">
                <i className="material-icons">menu</i>
              </a>
            </div>
          </nav>
        </div>
        <ul
          id="slide-out"
          className="sidenav"
          ref={sidenav => {
            this.sidenav = sidenav;
          }}
        >
          {links}
        </ul>
      </React.Fragment>
    );
  }
}

const mapStateToProps = state => {
  return {
    auth: state.firebase.auth,
    profile: state.firebase.profile
  };
};

export default connect(mapStateToProps)(Navbar);
