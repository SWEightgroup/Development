import React, { Component } from 'react';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';
import { ToastContainer, Flip } from 'react-toastify';

import { connect } from 'react-redux';
import Navbar from './view/containers/NavbarContainers/Navbar';
import Sidebar from './view/components/Sidebar';
import Dashboard from './view/containers/DashboardContainers/Dashboard';
import TeacherDashboard from './view/containers/DashboardContainers/TeacherDashboard';
import Footer from './view/components/Footer';
import SignUp from './view/containers/AuthContainers/SignUp';
import SignIn from './view/containers/AuthContainers/SignIn';
import Error from './view/components/Error';
import { initializeAuth, loaderOn } from './actions/AuthActions';
import { initializeNewExercise } from './actions/ExerciseActions';

import './App.css';
import FreeExercise from './view/containers/ExerciseContainers/FreeExercise';
import Account from './view/containers/AuthContainers/Account';
import Homework from './view/containers/ExerciseContainers/Homework';
import HomeworkExecution from './view/containers/ExerciseContainers/HomeworkExecution';
import InsertExercise from './view/containers/ExerciseContainers/InsertExercise';
import DeveloperDashBoard from './view/containers/DashboardContainers/DeveloperDashBoard';
import AdminDevDashBoard from './view/containers/DashboardContainers/AdminDevDashBoard';
import AdminUserDashBoard from './view/containers/DashboardContainers/AdminUserDashBoard';
import DoneHomework from './view/containers/ExerciseContainers/DoneHomework';
import AssignedHomework from './view/containers/ExerciseContainers/AssignedHomework';
import PublicExercises from './view/containers/ExerciseContainers/PublicExercises';
import ClassManagement from './view/containers/ExerciseContainers/ClassManagement';
import AdminDashboard from './view/containers/DashboardContainers/AdminDashboard';
import ExercisesDetails from './view/containers/ExerciseContainers/ExerciseDetails';
import FavouriteTeachers from './view/containers/ExerciseContainers/FavouriteTeachers';
import Activation from './view/containers/AuthContainers/Activation';
import Forgot from './view/containers/AuthContainers/Forgot';

class App extends Component {
  state = {};

  constructor(props) {
    super(props);
    const {
      initializeNewExerciseDispatch,
      loaderOnDispatch,
      initializeAuthDispatch,
      auth
    } = this.props;
    loaderOnDispatch();
    initializeAuthDispatch(auth.token);
    initializeNewExerciseDispatch();
  }

  render() {
    const { loader, innerLoader, auth, admin } = this.props;
    const { language, isReady } = auth;

    let mainDash = null;
    if (auth.user) {
      switch (auth.user.role) {
        case 'ROLE_TEACHER':
          mainDash = TeacherDashboard;
          break;
        case 'ROLE_STUDENT':
          mainDash = Dashboard;
          break;
        case 'ROLE_ADMIN':
          mainDash = AdminDashboard;
          break;
        case 'ROLE_DEVELOPER':
          mainDash = DeveloperDashBoard;
          break;
        default:
          mainDash = Dashboard;
      }
    }

    if (isReady) {
      return (
        <BrowserRouter>
          <div className="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
            <ToastContainer transition={Flip} />
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
                {auth.user && <Sidebar auth={auth} admin={admin} />}
              </div>
              <div className="app-main__outer ">
                <div className="app-main__inner relative full-height-mobile ">
                  {innerLoader && <div className="loading loading_inner" />}
                  <Switch>
                    <ProtectedRoute
                      exact
                      path="/"
                      component={mainDash}
                      isAllowed={auth.user}
                    />
                    <ProtectedRoute
                      exact
                      path="/forgot-password/:request?"
                      isAllowed={!auth.user}
                      component={Forgot}
                    />
                    <ProtectedRoute
                      path="/insert-exercise"
                      component={InsertExercise}
                      isAllowed={auth.user && auth.user.role === 'ROLE_TEACHER'}
                    />
                    <ProtectedRoute
                      path="/exercise"
                      component={FreeExercise}
                      isAllowed={auth.user && auth.user.role === 'ROLE_STUDENT'}
                    />
                    <Route
                      path="/sign-up/active/:activation?"
                      component={Activation}
                    />
                    <Route path="/signin" component={SignIn} />
                    <Route path="/signup" component={SignUp} />
                    <ProtectedRoute
                      path="/dashboard"
                      isAllowed={auth.user}
                      component={mainDash}
                    />
                    <ProtectedRoute
                      path="/account"
                      isAllowed={auth.user}
                      component={Account}
                    />
                    <ProtectedRoute
                      path="/grades"
                      isAllowed={auth.user && auth.user.role === 'ROLE_STUDENT'}
                      component={Account}
                    />
                    <ProtectedRoute
                      path="/homework"
                      isAllowed={auth.user && auth.user.role === 'ROLE_STUDENT'}
                      component={Homework}
                    />
                    <ProtectedRoute
                      path="/doneHomework"
                      isAllowed={auth.user && auth.user.role === 'ROLE_STUDENT'}
                      component={DoneHomework}
                    />
                    <ProtectedRoute
                      path="/favorite-teachers"
                      isAllowed={auth.user && auth.user.role === 'ROLE_STUDENT'}
                      component={FavouriteTeachers}
                    />
                    <ProtectedRoute
                      path="/assignedHomework"
                      isAllowed={auth.user && auth.user.role === 'ROLE_TEACHER'}
                      component={AssignedHomework}
                    />
                    <ProtectedRoute
                      path="/exerciseDetail/:exerciseId"
                      isAllowed={auth.user && auth.user.role === 'ROLE_TEACHER'}
                      component={ExercisesDetails}
                    />
                    <ProtectedRoute
                      path="/publicExercises"
                      isAllowed={auth.user && auth.user.role === 'ROLE_STUDENT'}
                      component={PublicExercises}
                    />
                    <ProtectedRoute
                      path="/homework-execution"
                      isAllowed={auth.user && auth.user.role === 'ROLE_STUDENT'}
                      component={HomeworkExecution}
                    />

                    <ProtectedRoute
                      path="/developers-management"
                      isAllowed={auth.user && auth.user.role === 'ROLE_ADMIN'}
                      component={AdminDevDashBoard}
                    />
                    <ProtectedRoute
                      path="/users-management"
                      isAllowed={auth.user && auth.user.role === 'ROLE_ADMIN'}
                      component={AdminUserDashBoard}
                    />
                    <ProtectedRoute
                      path="/class"
                      isAllowed={auth.user && auth.user.role === 'ROLE_TEACHER'}
                      component={ClassManagement}
                    />
                    <Route render={() => <Error language={language} />} />
                  </Switch>
                </div>
                <Footer />
              </div>
            </div>
          </div>
        </BrowserRouter>
      );
    }
    return <div className="loading" />;
  }
}

const ProtectedRoute = ({ isAllowed, ...props }) =>
  isAllowed ? <Route {...props} /> : <Redirect to="/signin" />;
const mapStateToProps = store => {
  return {
    auth: store.auth,
    loader: store.auth.loader,
    admin: store.admin,
    innerLoader: store.exercise.innerLoader
  };
};
const mapDispatchToProps = dispatch => {
  return {
    loaderOnDispatch: () => dispatch(loaderOn()),
    initializeAuthDispatch: token => dispatch(initializeAuth(token)),
    initializeNewExerciseDispatch: () => dispatch(initializeNewExercise())
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(App);
