import React, { Component } from 'react';
import { connect } from 'react-redux';
import { updateUserInfo } from '../../actions/AuthActions';
import { _translator } from '../components/Translator';

class Account extends Component {
  render() {
    const { userdata } = this.props;
    const { username, firstName, lastName } = userdata;
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-center">
          <div className="col-md-11">
            <div className="card">
              <h5 className="card-header">{_translator('account_yourData')}</h5>
              <div className="card-body">
                <form onSubmit={this.handleSubmit}>
                  <label htmlFor="firstName">
                    {_translator('gen_firstName')}
                  </label>
                  <div className="input-group mb-3">
                    <input
                      type="text"
                      className="form-control"
                      id="firstName"
                      defaultValue={firstName}
                    />
                  </div>
                  <label htmlFor="lastName">
                    {_translator('gen_lastName')}
                  </label>
                  <div className="input-group mb-3">
                    <input
                      type="text"
                      className="form-control"
                      id="lastName"
                      defaultValue={lastName}
                    />
                  </div>
                  <label htmlFor="username">{_translator('gen_email')}</label>
                  <div className="input-group mb-3">
                    <input
                      type="email"
                      className="form-control"
                      id="username"
                      defaultValue={username}
                    />
                  </div>
                  <h5 className="card-title">{_translator('gen_birthDate')}</h5>
                  <p className="card-text">Da implentare</p>
                  <h5 className="card-title">{_translator('gen_role')}</h5>
                  <p className="card-text">Da implentare</p>
                  <button type="submit" className="mt-2 btn btn-primary">
                    {_translator('gen_modify')}
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  handleSubmit = e => {
    e.preventDefault();
    /* const formData = {
      firstName: e.target.firstName.value,
      lastName: e.target.lastName.value,
      email: e.target.email.value
    }; */
    // ///////////////FARE CONTROLLO PER CARITA/////////////////////////////////
    updateUserInfo();
  };
}

const mapDispatchToProps = dispatch => {
  return {
    updateUserInfo: data => dispatch(updateUserInfo(data))
  };
};

const mapStateToProps = state => {
  return {
    userdata: state.auth.user
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Account);
