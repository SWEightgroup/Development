import React, { Component } from 'react';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';

import { connect } from 'react-redux';
import Navbar from './view/containers/NavbarContainers/Navbar';
import Sidebar from './view/components/Sidebar';
import Dashboard from './view/containers/DashboardContainers/Dashboard';
import Footer from './view/components/Footer';
import SignUp from './view/containers/AuthContainers/SignUp';
import SignIn from './view/containers/AuthContainers/SignIn';
import Error from './view/components/Error';
// import { initializeAuth } from './actions/AuthActions';

// import { loadAuth } from './actions/AuthActions';
import './App.css';
import NewExercise from './view/containers/ExerciseContainers/NewExercise';
import Account from './view/containers/AuthContainers/Account';
import Homework from './view/containers/ExerciseContainers/Homework';
import HomeworkExecution from './view/containers/ExerciseContainers/HomeworkExecution';
import InsertExercise from './view/containers/ExerciseContainers/InsertExercise';

class App extends Component {
  state = {};
  /* componentDidMount() {
    console.log(
      ': App -> componentDidMount -> this.props.auth',
      this.props.auth
    );
    this.props.initializeAuth(this.props.auth.token);
  } */

  render() {
    const { loader, auth } = this.props;
    const { language } = auth;
    return (
      <BrowserRouter>
        <div className="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
          {loader && <div className="loading" />}
          <Navbar />
          <div className="app-main">
            <div className="app-sidebar sidebar-shadow">
              <div className="app-header__logo">
                <div className="logo-src" />
                <div className="header__pane ml-auto">
                  <div>
                    <button
                      type="button"
                      className="hamburger close-sidebar-btn hamburger--elastic "
                      data-class="closed-sidebar"
                    >
                      <span className="hamburger-box">
                        <span className="hamburger-inner" />
                      </span>
                    </button>
                  </div>
                </div>
              </div>
              <div className="app-header__mobile-menu">
                <div>
                  <button
                    type="button"
                    className="hamburger hamburger--elastic mobile-toggle-nav"
                  >
                    <span className="hamburger-box">
                      <span className="hamburger-inner" />
                    </span>
                  </button>
                </div>
              </div>
              {auth.user && <Sidebar auth={auth} />}
            </div>
            <div className="app-main__outer">
              <Switch>
                <ProtectedRoute
                  exact
                  path="/"
                  component={Dashboard}
                  isAllowed={auth.user}
                />
                <ProtectedRoute
                  path="/insert-exercise"
                  component={InsertExercise}
                  isAllowed={auth.user}
                />
                <ProtectedRoute
                  path="/exercise"
                  component={NewExercise}
                  isAllowed={
                    auth.user /* && auth.user.role === 'ROLE_STUDENT' */
                  }
                />
                <Route path="/signin" component={SignIn} />
                <Route path="/signup" component={SignUp} />
                <ProtectedRoute
                  path="/dashboard"
                  isAllowed={auth.user}
                  component={Dashboard}
                />
                <ProtectedRoute
                  path="/account"
                  isAllowed={auth.user}
                  component={Account}
                />
                <ProtectedRoute
                  path="/teachers"
                  isAllowed={auth.user}
                  component={Account}
                />
                <ProtectedRoute
                  path="/grades"
                  isAllowed={auth.user}
                  component={Account}
                />
                <ProtectedRoute
                  path="/homework"
                  isAllowed={auth.user}
                  component={Homework}
                />
                <ProtectedRoute
                  path="/homework-execution"
                  isAllowed={
                    auth.user /* && auth.user.role === 'ROLE_STUDENT' */
                  }
                  component={HomeworkExecution}
                />
                <Route render={() => <Error language={language} />} />
              </Switch>
              <Footer />
            </div>
          </div>
        </div>
      </BrowserRouter>
    );
  }
}

const ProtectedRoute = ({ isAllowed, ...props }) =>
  isAllowed ? <Route {...props} /> : <Redirect to="/signin" />;

const mapStateToProps = state => {
  return {
    auth: state.auth,
    loader: state.auth.loader
  };
};
const mapDispatchToProps = dispatch => {
  return {
    // initializeAuth: token => dispatch(initializeAuth(token))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(App);
