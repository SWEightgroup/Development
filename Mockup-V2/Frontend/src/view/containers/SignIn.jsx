import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { signIn, loaderOn, changeSignIn } from '../../actions/AuthActions';
import _translator from '../../helpers/Translator';

class SignIn extends Component {
  handleChange = e => {
    changeSignIn({ [e.target.id]: e.target.value });
  };

  handleSubmit = e => {
    const { signIn, loaderOn, signInData } = this.props;
    loaderOn();
    e.preventDefault();
    signIn(signInData);
  };

  render() {
    const { auth, signInData } = this.props;

    if (auth && auth.user) return <Redirect to="/dashboard" />;
    return (
      <div className="app-main__inner full-height-mobile ">
        <div className="row justify-content-md-center">
          <div className="col-sm-12 col-md-6">
            <div className="main-card mb-3 card">
              <div className="card-body">
                <h5 className="card-title">{_translator('gen_signin')}</h5>

                <form onSubmit={this.handleSubmit}>
                  <div className="position-relative form-group">
                    <label htmlFor="email">{_translator('gen_email')}</label>
                    <input
                      name="username"
                      id="username"
                      placeholder={_translator('gen_email')}
                      type="email"
                      className="form-control"
                      value={signInData.email}
                      onChange={this.handleChange}
                      autoComplete="username"
                      required
                    />
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="password">
                      {_translator('gen_password')}
                    </label>
                    <input
                      name="password"
                      id="password"
                      placeholder={_translator('gen_password')}
                      type="password"
                      className="form-control"
                      onChange={this.handleChange}
                      required
                      autoComplete="current-password"
                    />
                  </div>
                  <button type="submit" className="mt-2 btn btn-primary">
                    {_translator('gen_signin')}
                  </button>
                  <div className="">
                    {auth.signinError ? <p>{auth.signinError}</p> : null}
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
    signinError: store.auth.signinError,
    auth: store.auth,
    signInData: store.auth.signIn
  };
};

const mapDispatchToProps = dispatch => {
  return {
    signIn: credentials => dispatch(signIn(credentials)),
    loaderOn: () => dispatch(loaderOn()),
    changeSignIn: data => dispatch(changeSignIn(data))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SignIn);
