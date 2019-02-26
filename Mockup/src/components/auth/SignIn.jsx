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
    console.log('ciaoooo');
    e.preventDefault();
    signIn(this.state);
  };

  render() {
    const { authError, auth } = this.props;
    if (auth.uid) return <Redirect to="/" />;
    return (
      <div className="app-main__inner">
        <div className="row">
          <div className="col-sm-12 col-md-8 col-lg-6">
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
                      onChange={this.handleChange}
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
                    />
                  </div>
                  <button type="submit" className="mt-2 btn btn-primary">
                    Accedi
                  </button>
                  <div className="">
                    {authError ? <p>{authError}</p> : null}
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
