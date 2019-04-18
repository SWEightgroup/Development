import React, { Component } from 'react';
import { connect } from 'react-redux';
import SignedInLinks from "../../components/SignedInLinks";
import SignedOutLinks from "../../components/SignedOutLinks";

class Navbar extends Component {
  state = {};

  render() {
    const { auth } = this.props;
    const links = auth.user ? (
      <SignedInLinks auth={auth} />
    ) : (
      <SignedOutLinks />
    );

    return (
      <div className="app-header header-shadow">
        <div className="app-header__logo">
          <div className="logo-src" />
          <div className="header__pane ml-auto">
            <div>
              <button
                type="button"
                className="hamburger close-sidebar-btn hamburger--elastic is-active"
                data-class="closed-sidebar"
              >
                <span className="hamburger-box">
                  <span className="hamburger-inner" />
                </span>
              </button>
            </div>
          </div>
        </div>
        <div className="app-header__mobile-menu">
          <div>
            <button
              type="button"
              className="hamburger hamburger--elastic mobile-toggle-nav"
            >
              <span className="hamburger-box">
                <span className="hamburger-inner" />
              </span>
            </button>
          </div>
        </div>
        <div className="app-header__menu">
          <span>
            <button
              type="button"
              className="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav"
            >
              <span className="btn-icon-wrapper">
                <i className="material-icons">person_outline</i>
              </span>
            </button>
          </span>
        </div>
        <div className="app-header__content">{links}</div>
      </div>
    );
  }
}

const mapStateToProps = store => {
  return {
    auth: store.auth
  };
};

export default connect(mapStateToProps)(Navbar);
