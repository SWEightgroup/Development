import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';
import NewExsercise from '../exercise/NewExercise';

class Body extends Component {
  state = {};

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const { auth } = this.props;
    if (!auth.user) return <Redirect to="/signin" />;
    return (
      <div className="app-main__inner full-height-mobile">
        <NewExsercise />
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    auth: state.auth
  };
};
export default connect(mapStateToProps)(Body);
