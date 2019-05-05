import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { v4 } from 'node-uuid';

import { activeAccount } from '../../../actions/AuthActions';
import HelperMessage from '../../components/HelperMessage';

class Activation extends Component {
  constructor(props) {
    super(props);
    if (props.match.params && props.match.params.activation) {
      props.activeAccountDispatch(props.match.params.activation);
    } else {
      props.history.push('signin');
    }
  }

  render() {
    const { auth } = this.props;

    if (auth && auth.user) return <Redirect to="/dashboard" />;
    return (
      <HelperMessage>
        {auth.activationMessage &&
          auth.activationMessage.map(message => <h6 key={v4()}>{message}</h6>)}
      </HelperMessage>
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
    activeAccountDispatch: signUpRequestId =>
      dispatch(activeAccount(signUpRequestId))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Activation);
