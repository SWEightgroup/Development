import React from "react";
import { NavLink } from "react-router-dom";
import { connect } from "react-redux";
import { signOut } from "../../store/actions/authActions";

const SignedInLinks = props => {
  const { auth, profile } = props;
  return (
    <React.Fragment>
      {/* da visualizzare solo nella parte mobile */}
      <li>
        <div className="user-view hide-on-large-only">
          <div className="background">
            <img
              src="https://www.tp24.it/immagini_articoli/11-10-2018/1539294063-0-fine-settimana-instabile-provincia-trapani-tornano-nuvole-pioggia.jpg"
              alt="nuvole"
            />
          </div>

          {profile.linkPhoto ? (
            <NavLink to="/profile" className="sidenav-close">
              <img className="circle" src={profile.linkPhoto} alt={profile.firstName + " " + profile.lastName + " photo profile"} />
            </NavLink>
          ) : (
            <NavLink to="/profile" className="sidenav-close pink circle white-text valign-wrapper circle-initial">
              {profile.initials}
            </NavLink>
          )}

          <NavLink to="/profile" className="sidenav-close">
            <span className="white-text name">
              {profile.firstName} {profile.lastName}
            </span>
          </NavLink>
          <span>
            <span className="white-text email">{auth.email}</span>
          </span>
        </div>
      </li>
      {/* ----------------------------------------------- */}

      <li>
        <NavLink to="/create" className="sidenav-close">
          Aggiungi
        </NavLink>
      </li>
      <li>
        <NavLink to="/signin" onClick={props.signOut} className="sidenav-close pointer">
          Esci
        </NavLink>
      </li>
      <li>
        <NavLink to="/profile" className="btn btn-floating pink lighten-1 hide-on-med-and-down sidenav-close">
          {profile.linkPhoto ? (
            <img className="responsive-img" src={profile.linkPhoto} alt={profile.firstName + " " + profile.lastName + " photo profile"} />
          ) : (
            profile.initials
          )}
        </NavLink>
      </li>
    </React.Fragment>
  );
};

const mapStateToProps = state => {
  return {
    auth: state.firebase.auth
  };
};

const mapDispatchToProps = dispatch => {
  return {
    signOut: () => dispatch(signOut())
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SignedInLinks);
