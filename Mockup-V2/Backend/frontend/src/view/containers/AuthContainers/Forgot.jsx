import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';

import {
  changeForgot,
  loaderOn,
  changePassword,
  sendRequest
} from '../../../actions/AuthActions';
import _translator from '../../../helpers/Translator';
import HelperMessage from '../../components/HelperMessage';

class Forgot extends Component {
  constructor(props) {
    super(props);
    const request = props.match.params ? props.match.params.request : '';
    props.changeForgotDispatch({
      username: '',
      password: '',
      passwordConfirm: '',
      requestSent: false,
      passwordChanged: false,
      requestId: request
    });
  }

  handleChange = e => {
    const { changeForgotDispatch } = this.props;
    changeForgotDispatch({ [e.target.id]: e.target.value });
  };

  handleSubmitPassword = e => {
    const { props } = this;
    e.preventDefault();
    props.loaderOn();
    props.changePasswordDispatch(props.forgot);
  };

  handleSubmitEmail = e => {
    const { props } = this;
    e.preventDefault();
    props.loaderOn();
    props.sendRequestDispatch(props.forgot);
  };

  render() {
    const { auth, forgot, match } = this.props;

    const {
      username,
      password,
      passwordConfirm,
      requestSent,
      passwordChanged
    } = forgot;

    if (auth && auth.user) return <Redirect to="/dashboard" />;

    if (!match.params || !match.params.request) {
      if (!requestSent) {
        return (
          <div className="row justify-content-md-center">
            <div className="col-sm-12 col-md-6">
              <div className="main-card mb-3 card">
                <div className="card-body">
                  <h5 className="card-title">
                    {_translator('forgot_changePassword')}
                  </h5>

                  <form onSubmit={this.handleSubmitEmail}>
                    <p>{_translator('forgot_info')}</p>
                    <div className="position-relative form-group">
                      <label htmlFor="email">{_translator('gen_email')}</label>
                      <input
                        name="username"
                        id="username"
                        placeholder={_translator('gen_email')}
                        type="email"
                        className="form-control"
                        value={username}
                        onChange={this.handleChange}
                        required
                      />
                    </div>
                    <button type="submit" className="mt-2 btn btn-primary">
                      {_translator('forgot_sendRequest')}
                    </button>
                    <div className="">
                      {auth.signinError ? <p>{auth.signinError}</p> : null}
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        );
      }
      return <HelperMessage>{_translator('forgot_info')}</HelperMessage>;
    }
    if (!passwordChanged) {
      return (
        <div className="row justify-content-md-center">
          <div className="col-sm-12 col-md-6">
            <div className="main-card mb-3 card">
              <div className="card-body">
                <h5 className="card-title">
                  {_translator('forgot_insNewPass')}
                </h5>

                <form onSubmit={this.handleSubmitPassword}>
                  <div className="position-relative form-group">
                    <label htmlFor="email">{_translator('gen_password')}</label>
                    <input
                      name="password"
                      id="password"
                      placeholder={_translator('gen_password')}
                      type="password"
                      className="form-control"
                      value={password}
                      onChange={this.handleChange}
                      required
                    />
                  </div>
                  <div className="position-relative form-group">
                    <label htmlFor="email">
                      {_translator('gen_passwordConfirm')}
                    </label>
                    <input
                      name="passwordConfirm"
                      id="passwordConfirm"
                      placeholder={_translator('gen_passwordConfirm')}
                      type="password"
                      className="form-control"
                      value={passwordConfirm}
                      onChange={this.handleChange}
                      required
                    />
                  </div>
                  <button type="submit" className="mt-2 btn btn-primary">
                    {_translator('forgot_changePassword')}
                  </button>
                  <div className="">
                    {auth.signinError ? <p>{auth.signinError}</p> : null}
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      );
    }
    return (
      <HelperMessage>{_translator('forgot_changedSuccess')}</HelperMessage>
    );
  }
}

const mapStateToProps = store => {
  return {
    signinError: store.auth.signinError,
    auth: store.auth,
    forgot: store.auth.forgot
  };
};

const mapDispatchToProps = dispatch => {
  return {
    changeForgotDispatch: variable => dispatch(changeForgot(variable)),
    loaderOn: () => dispatch(loaderOn()),
    changePasswordDispatch: data => dispatch(changePassword(data)),
    sendRequestDispatch: data => dispatch(sendRequest(data))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Forgot);
