import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import {
  signUp,
  loaderOn,
  changeSignUp,
  displayError
} from '../../actions/AuthActions';
import { _translator } from '../components/Translator';

class SignUp extends Component {
  handleChange = e => {
    changeSignUp({ [e.target.id]: e.target.value });
  };

  handleSubmit = e => {
    const { signUpDispatch, loaderOn, auth, displayErrorDispatch } = this.props;
    e.preventDefault();

    const signUpData = auth.signUp;

    if (!signUpData.password.localeCompare(signUpData.password_confirm)) {
      loaderOn();
      signUpDispatch();
    } else {
      displayErrorDispatch(_translator('signup_errorPassword'));
    }
  };

  render() {
    const { auth } = this.props;
    const signUpData = auth.signUp;

    if (auth.user) return <Redirect to="/" />;
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-md-center">
          <div className="col-sm-12 col-md-8 col-lg-8">
            <div className="main-card mb-3 card">
              <div className="card-body">
                <h5 className="card-title">{_translator('gen_signup')}</h5>

                <form onSubmit={this.handleSubmit}>
                  <div className="position-relative form-group">
                    <label htmlFor="firstName">
                      {_translator('gen_firstName')}
                    </label>
                    <input
                      name="firstName"
                      id="firstName"
                      placeholder={_translator('gen_firstName')}
                      type="text"
                      className="form-control"
                      onChange={this.handleChange}
                      value={signUpData.firstName}
                    />
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="firstName">
                      {_translator('gen_lastName')}
                    </label>
                    <input
                      name="lastName"
                      id="lastName"
                      placeholder={_translator('gen_lastName')}
                      type="text"
                      className="form-control"
                      onChange={this.handleChange}
                      value={signUpData.lastName}
                    />
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="email">{_translator('gen_email')}</label>
                    <input
                      name="username"
                      id="username"
                      placeholder={_translator('gen_email')}
                      type="email"
                      className="form-control"
                      onChange={this.handleChange}
                      value={signUpData.email}
                      autoComplete="username"
                    />
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="password">
                      {_translator('gen_password')}
                    </label>
                    <input
                      name="passowrd"
                      id="password"
                      minLength="6"
                      placeholder={_translator('gen_password')}
                      type="password"
                      className="form-control"
                      onChange={this.handleChange}
                      autoComplete="new-password"
                    />
                    <small
                      id="passwordHelpBlock"
                      className="form-text text-muted"
                    >
                      {_translator('signup_hint')}
                    </small>
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="password_confirm">
                      {_translator('gen_passwordConfirm')}
                    </label>
                    <input
                      name="password_confirm"
                      id="password_confirm"
                      minLength="6"
                      placeholder={_translator('gen_passwordConfirm')}
                      type="password"
                      className="form-control"
                      onChange={this.handleChange}
                    />
                  </div>

                  <button type="submit" className="mt-2 btn btn-primary">
                    {_translator('gen_signup')}
                  </button>
                  <div>{auth.authError ? <p>{auth.authError}</p> : null}</div>
                </form>
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
    auth: store.auth
  };
};

const mapDispatchToProps = dispatch => {
  return {
    signUpDispatch: () => dispatch(signUp()),
    loaderOn: () => dispatch(loaderOn()),
    changeSignUp: () => dispatch(changeSignUp()),
    displayErrorDispatch: error => dispatch(displayError(error))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SignUp);
