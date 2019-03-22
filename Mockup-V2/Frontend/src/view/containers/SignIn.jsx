import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { signIn, loaderOn, changeSignIn } from '../../actions/AuthActions';

class SignIn extends Component {
  handleChange = e => {
    changeSignIn({ [e.target.id]: e.target.value });
  };

  handleSubmit = e => {
    const { signIn, loaderOn } = this.props;
    loaderOn();
    e.preventDefault();
    signIn();
  };

  render() {
    const { auth, signInData } = this.props;
    if (auth && auth.user) return <Redirect to="/" />;
    return (
      <div className="app-main__inner full-height-mobile ">
        <div className="row justify-content-md-center">
          <div className="col-sm-12 col-md-6">
            <div className="main-card mb-3 card">
              <div className="card-body">
                <h5 className="card-title">Login</h5>

                <form onSubmit={this.handleSubmit}>
                  <div className="position-relative form-group">
                    <label htmlFor="email">Email</label>
                    <input
                      name="address"
                      id="email"
                      placeholder="Email"
                      type="email"
                      className="form-control"
                      value={signInData.email}
                      onChange={this.handleChange}
                      required
                    />
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="password">Password</label>
                    <input
                      name="address2"
                      id="password"
                      placeholder="Passowrd"
                      type="password"
                      className="form-control"
                      onChange={this.handleChange}
                      value={signInData.password}
                      required
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
    authError: store.auth.authError,
    auth: store.auth,
    signInData: store.auth.signIn
  };
};

const mapDispatchToProps = dispatch => {
  return {
    signIn: () => dispatch(signIn()),
    loaderOn: () => dispatch(loaderOn()),
    changeSignIn: data => dispatch(changeSignIn(data))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SignIn);
