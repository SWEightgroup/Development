import React, { Component } from 'react';
import { connect } from 'react-redux';
import { updateUserInfo } from '../../actions/AuthActions';

class Account extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const { userdata } = this.props;
    const { email, firstName, lastName } = userdata;
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row">
          <div className="col-md-11">
            <div className="card">
              <h5 className="card-header">I tuoi dati</h5>
              <div className="card-body">
                <form onSubmit={this.handleSubmit}>
                  <label htmlFor="firstName">Nome</label>
                  <div className="input-group mb-3">
                    <input
                      type="text"
                      className="form-control"
                      id="firstName"
                      defaultValue={firstName}
                    />
                  </div>
                  <label htmlFor="lastName">Cognome</label>
                  <div className="input-group mb-3">
                    <input
                      type="text"
                      className="form-control"
                      id="lastName"
                      defaultValue={lastName}
                    />
                  </div>
                  <label htmlFor="email">Email</label>
                  <div className="input-group mb-3">
                    <input
                      type="text"
                      className="form-control"
                      id="email"
                      defaultValue={email}
                    />
                  </div>
                  <h5 className="card-title">Data di nascita</h5>
                  <p className="card-text">Da implentare</p>
                  <h5 className="card-title">Ruolo</h5>
                  <p className="card-text">Da implentare</p>
                  <button type="submit" className="mt-2 btn btn-primary">
                    Modifica
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
    userdata: state.auth.user.profile
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Account);
