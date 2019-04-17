import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import { downlaodAll } from '../../../actions/AdminActions';

class DeveloperDashboard extends Component {
  state = {};

  render() {
    const { user, downlaodAllDispatch } = this.props;
    const { language } = user;
    return (
      <React.Fragment>
        <div className="row justify-content-center">
          <div className="py-5 text-center">
            <h2>{_translator('gen_devDashboard', language)}</h2>
            <p className="lead">
              {_translator('developerDashBoard_desc', language)}
            </p>
          </div>
        </div>
        <div className="row justify-content-center">
          <div className="col-md-11">
            <div className="card">
              <h5 className="card-header">
                {_translator('gen_devDashboard', language)}
              </h5>
              <div className="card-body">
                <p>{_translator('developerDashBoard_devDownText', language)}</p>
                <button
                  type="button"
                  onClick={() => downlaodAllDispatch()}
                  className="btn btn-primary"
                >
                  {_translator('developerDashBoard_devDown', language)}
                </button>
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

const mapStateToProps = store => {
  return {
    user: store.auth.user
  };
};

const mapDispatchToProps = dispatch => {
  return {
    downlaodAllDispatch: () => dispatch(downlaodAll())
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DeveloperDashboard);
