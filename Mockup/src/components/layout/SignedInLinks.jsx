import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { connect } from 'react-redux';
import { signOut } from '../../store/actions/AuthActions';

const SignedInLinks = props => {
  const { profile } = props;
  return (
    <React.Fragment>
      <div className="app-header-right">
        <div className="header-btn-lg pr-0">
          <div className="widget-content p-0">
            <div className="widget-content-wrapper">
              <div className="widget-content-left">
                <div className="btn-group">
                  <a
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                    className="p-0 btn"
                    href=""
                  >
                    {/* <img
                      width="42"
                      className="rounded-circle"
                      src="assets/images/avatars/1.jpg"
                      alt=""
                    /> */}
                    <span className="rounded-circle bg-info py-1 px-2 h3 text-light">
                      {profile.initials}
                    </span>
                  </a>
                  <div
                    tabIndex="-1"
                    role="menu"
                    aria-hidden="true"
                    className="dropdown-menu dropdown-menu-right"
                  >
                    <button
                      type="button"
                      tabIndex="0"
                      className="dropdown-item"
                    >
                      Account
                    </button>
                    <button
                      type="button"
                      tabIndex="0"
                      className="dropdown-item"
                    >
                      Impostazioni
                    </button>
                    <NavLink
                      to="/signin"
                      onClick={props.signOut}
                      tabIndex="0"
                      className="dropdown-item"
                    >
                      Esci
                    </NavLink>
                  </div>
                </div>
              </div>
              <div className="widget-content-left  ml-3 header-user-info">
                <div className="widget-heading">
                  {profile.firstName} {profile.lastName}
                </div>
              </div>
              <div className="widget-content-right header-user-info ml-3" />
            </div>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

const mapDispatchToProps = dispatch => {
  return {
    signOut: () => dispatch(signOut())
  };
};

export default connect(
  null,
  mapDispatchToProps
)(SignedInLinks);
