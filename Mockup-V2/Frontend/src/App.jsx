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
import { initializeAuth, loaderOn } from './actions/AuthActions';

import './App.css';
import NewExercise from './view/containers/ExerciseContainers/NewExercise';
import Account from './view/containers/AuthContainers/Account';
import Homework from './view/containers/ExerciseContainers/Homework';
import HomeworkExecution from './view/containers/ExerciseContainers/HomeworkExecution';
import InsertExercise from './view/containers/ExerciseContainers/InsertExercise';
import DeveloperDashBoard from './view/containers/DashboardContainers/DeveloperDashBoard';
import AdminDevDashBoard from './view/containers/DashboardContainers/AdminDevDashBoard';
import AdminUserDashBoard from './view/containers/DashboardContainers/AdminUserDashBoard';
import DoneHomework from './view/containers/ExerciseContainers/DoneHomework';

class App extends Component {
  state = {};

  constructor(props) {
    super(props);
    this.props.loaderOn();
    this.props.initializeAuth(this.props.auth.token);
  }

  render() {
    const { loader, innerLoader, auth } = this.props;
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
              {innerLoader && <div className="loading" />}
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
                  path="/doneHomework"
                  isAllowed={auth.user}
                  component={DoneHomework}
                />
                <ProtectedRoute
                  path="/homework-execution"
                  isAllowed={
                    auth.user /* && auth.user.role === 'ROLE_STUDENT' */
                  }
                  component={HomeworkExecution}
                />
                <ProtectedRoute
                  path="/developer-dashboard"
                  isAllowed={
                    auth.user /* && auth.user.role === 'ROLE_DEVELOPER' */
                  }
                  component={DeveloperDashBoard}
                />
                <ProtectedRoute
                  path="/developers-management"
                  isAllowed={auth.user /* && auth.user.role === 'ROLE_ADMIN' */}
                  component={AdminDevDashBoard}
                />
                <ProtectedRoute
                  path="/users-management"
                  isAllowed={auth.user /* && auth.user.role === 'ROLE_ADMIN' */}
                  component={AdminUserDashBoard}
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
    loader: state.auth.loader,
    innerLoader: state.exercise.innerLoader
  };
};
const mapDispatchToProps = dispatch => {
  return {
    loaderOn: () => dispatch(loaderOn()),
    initializeAuth: token => dispatch(initializeAuth(token))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(App);
