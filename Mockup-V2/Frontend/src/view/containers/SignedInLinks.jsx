import React from 'react';
import { NavLink } from 'react-router-dom';
import { connect } from 'react-redux';
import { signOut } from '../../actions/AuthActions';

const SignedInLinks = props => {
  const { user, signOut } = props;
  return (
    <React.Fragment>
      <div className="app-header-right">
        <div className="header-btn-lg pr-0">
          <div className="widget-content p-0">
            <div className="widget-content-wrapper">
              <div className="widget-content-left">
                <ul className="header-menu nav">
                  <li className="nav-item">
                    <NavLink to="/" className="nav-link">
                      Account
                    </NavLink>
                  </li>
                  <li className="btn-group nav-item">
                    <NavLink
                      to="/signin"
                      onClick={signOut}
                      tabIndex="0"
                      className="dropdown-item"
                    >
                      Esci
                    </NavLink>
                  </li>
                </ul>
              </div>

              {/** <div className="widget-content-left">
                <div className="btn-group">
                  <button
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                    className="p-0 btn  rounded-circle btn-initial bg-blue-template text-white"
                    type="button"
                  >
                    { <img
                      width="42"
                      className="rounded-circle"
                      src="assets/images/avatars/1.jpg"
                      alt=""
                    /> }
                    <span className="h4">{profile.initials}</span>
                  </button>
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
                      onClick={signOutDispatch}
                      tabIndex="0"
                      className="dropdown-item"
                    >
                      Esci
                    </NavLink>
                  </div>
                </div>
              </div> */}
              <div className="widget-content-left  ml-3 header-user-info">
                <div className="widget-heading">
                  {user.profile.firstName} {user.profile.lastName}
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
