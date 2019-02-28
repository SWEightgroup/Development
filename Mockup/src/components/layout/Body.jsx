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
    console.log(auth);
    if (!auth.uid) return <Redirect to="/signin" />;
    return (
      <div className="app-main__inner">
        <NewExsercise />
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    auth: state.firebase.auth
  };
};

export default connect(mapStateToProps)(Body);
