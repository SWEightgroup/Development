import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import { signUp } from '../../store/actions/AuthActions';

class SignUp extends Component {
  state = {
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    linkPhoto: ''
  };

  handleChange = e => {
    this.setState({
      [e.target.id]: e.target.value
    });
  };

  handleSubmit = e => {
    e.preventDefault();
    signUp(this.state);
  };

  render() {
    const { auth, authError } = this.props;
    if (auth.uid) return <Redirect to="/" />;
    return (
      <div className="container">
        <form className="white" onSubmit={this.handleSubmit}>
          <h5 className="grey-text text-darken-3">Registrazione</h5>
          <div className="input-field">
            <label htmlFor="email">
              Email
              <input type="email" id="email" onChange={this.handleChange} />
            </label>
          </div>
          <div className="input-field">
            <label htmlFor="password">
              Password
              <input
                type="password"
                id="password"
                onChange={this.handleChange}
              />
            </label>
          </div>
          <div className="input-field">
            <label htmlFor="firstName">
              Nome
              <input type="text" id="firstName" onChange={this.handleChange} />
            </label>
          </div>
          <div className="input-field">
            <label htmlFor="lastName">
              Cognome
              <input type="text" id="lastName" onChange={this.handleChange} />
            </label>
          </div>
          <div className="input-field">
            <label htmlFor="linkPhoto">
              Link di un immagine per la foto profilo
              <input type="text" id="linkPhoto" onChange={this.handleChange} />
            </label>
          </div>
          <div className="input-field">
            <button type="button" className="btn pink lighten-1 z-depth-0">
              Registrati
            </button>
            <div className="center red-text">
              {authError ? <p>{authError}</p> : null}
            </div>
          </div>
        </form>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    auth: state.firebase.auth,
    authError: state.auth.authError
  };
};

const mapDispatchToProps = dispatch => {
  return {
    signUp: creds => dispatch(signUp(creds))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SignUp);
