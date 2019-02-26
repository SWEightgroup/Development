import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { signIn } from '../../store/actions/AuthActions';

class SignIn extends Component {
  state = {
    email: '',
    password: ''
  };

  handleChange = e => {
    this.setState({
      [e.target.id]: e.target.value
    });
  };

  handleSubmit = e => {
    e.preventDefault();
    signIn(this.state);
  };

  render() {
    const { authError, auth } = this.props;
    if (auth.uid) return <Redirect to="/" />;
    return (
      <div className="container">
        <form className="white" onSubmit={this.handleSubmit}>
          <h5 className="grey-text text-darken-3">Accedi</h5>
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
            <button type="button" className="btn pink lighten-1 z-depth-0">
              Login
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
    authError: state.auth.authError,
    auth: state.firebase.auth
  };
};

const mapDispatchToProps = dispatch => {
  return {
    signIn: creds => dispatch(signIn(creds))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SignIn);
