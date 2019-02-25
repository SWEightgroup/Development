import React, { Component } from "react";
import { connect } from "react-redux";
import { Redirect } from "react-router-dom";
import M from "materialize-css/dist/js/materialize";
import { saveProfile } from "../../store/actions/authActions";

class Profile extends Component {
  state = {
    email: this.props.auth.email,
    firstName: this.props.profile.firstName,
    lastName: this.props.profile.lastName,
    linkPhoto: this.props.profile.linkPhoto
  };
  componentDidMount() {
    this.email.value = this.props.auth.email;
    this.lastName.value = this.props.profile.lastName ? this.props.profile.lastName : "";
    this.firstName.value = this.props.profile.firstName ? this.props.profile.firstName : "";
    this.linkPhoto.value = this.props.profile.linkPhoto ? this.props.profile.linkPhoto : "";

    M.updateTextFields();
  }

  handleChange = e => {
    this.setState({
      [e.target.id]: e.target.value
    });
  };
  handleSubmit = e => {
    e.preventDefault();
    this.props.saveProfile({ ...this.state, uid: this.props.auth.uid });
  };

  render() {
    const { auth, profile, authError } = this.props;

    if (!auth.uid) return <Redirect to="/signin" />;
    return (
      <div className="container">
        <form className="white" onSubmit={this.handleSubmit}>
          <h5 className="grey-text text-darken-3">
            {profile.firstName} {profile.lastName}
          </h5>
          <div className="input-field">
            <label htmlFor="email">Email</label>
            <input type="email" id="email" ref={email => (this.email = email)} onChange={this.handleChange} required />
          </div>
          <div className="input-field">
            <label htmlFor="firstName">First Name</label>
            <input type="text" id="firstName" ref={firstName => (this.firstName = firstName)} onChange={this.handleChange} required />
          </div>
          <div className="input-field">
            <label htmlFor="lastName">Last Name</label>
            <input type="text" id="lastName" ref={lastName => (this.lastName = lastName)} onChange={this.handleChange} required />
          </div>
          <div className="input-field">
            <label htmlFor="linkPhoto">Link photo profile</label>
            <input type="text" id="linkPhoto" ref={linkPhoto => (this.linkPhoto = linkPhoto)} onChange={this.handleChange} />
          </div>
          <div className="input-field">
            <button className="btn pink lighten-1 z-depth-0">Save</button>
            <div className="center green-text">{authError ? <p>{authError}</p> : null}</div>
          </div>
        </form>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    auth: state.firebase.auth,
    profile: state.firebase.profile,
    authError: state.auth.authError
  };
};

const mapDispatchToProps = dispatch => {
  return {
    saveProfile: profile => dispatch(saveProfile(profile))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Profile);
