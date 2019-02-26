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
    const { signUp } = this.props;
    e.preventDefault();
    signUp(this.state);
  };

  render() {
    const { auth, authError } = this.props;
    if (auth.uid) return <Redirect to="/" />;
    return (
      <div className="app-main__inner">
        <div className="row">
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
                  <div className="position-relative form-group">
                    <label htmlFor="linkPhoto">Link immagine del profile</label>
                    <input
                      name="linkPhoto"
                      id="linkPhoto"
                      placeholder="http://ilmiosito.it/laMiaImmagine.jpg"
                      type="text"
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
