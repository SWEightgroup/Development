import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import {
  signUp,
  loaderOn,
  changeSignUp,
  displayError
} from '../../actions/AuthActions';

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
      displayErrorDispatch('Le due password non coincidono');
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
                <h5 className="card-title">Login</h5>

                <form onSubmit={this.handleSubmit}>
                  <div className="position-relative form-group">
                    <label htmlFor="firstName">Nome</label>
                    <input
                      name="firstName"
                      id="firstName"
                      placeholder="Nome"
                      type="text"
                      className="form-control"
                      onChange={this.handleChange}
                      value={signUpData.firstName}
                    />
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="firstName">Cognome</label>
                    <input
                      name="lastName"
                      id="lastName"
                      placeholder="Cognome"
                      type="text"
                      className="form-control"
                      onChange={this.handleChange}
                      value={signUpData.lastName}
                    />
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="email">Email</label>
                    <input
                      name="address"
                      id="email"
                      placeholder="Email"
                      type="email"
                      className="form-control"
                      onChange={this.handleChange}
                      value={signUpData.email}
                    />
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="password">Password</label>
                    <input
                      name="address2"
                      id="password"
                      minLength="6"
                      placeholder="Passowrd"
                      type="password"
                      className="form-control"
                      onChange={this.handleChange}
                    />
                    <small
                      id="passwordHelpBlock"
                      className="form-text text-muted"
                    >
                      Your password must be 6-20 characters long, contain
                      letters and numbers, and must not contain spaces, special
                      characters, or emoji.
                    </small>
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="password_confirm">Conferma Password</label>
                    <input
                      name="address2"
                      id="password_confirm"
                      minLength="6"
                      placeholder="Conferma Password"
                      type="password"
                      className="form-control"
                      onChange={this.handleChange}
                    />
                  </div>

                  <button type="submit" className="mt-2 btn btn-primary">
                    Accedi
                  </button>
                  <div className="">
                    {auth.authError ? <p>{auth.authError}</p> : null}
                  </div>
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
