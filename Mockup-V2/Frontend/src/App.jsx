import React, { Component } from 'react';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import Navbar from './view/containers/Navbar';
import Sidebar from './view/components/Sidebar';
import Dashboard from './view/containers/Dashboard';
import Footer from './view/components/Footer';
import SignUp from './view/containers/SignUp';
import SignIn from './view/containers/SignIn';
import Error from './view/components/Error';

// import { loadAuth } from './actions/AuthActions';
import './App.css';
import NewExercise from './view/containers/NewExercise';
import Account from './view/containers/Account';

class App extends Component {
  render() {
    const { loader, auth } = this.props;
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
                  path="/exercise"
                  component={NewExercise}
                  isAllowed={auth.user}
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
                <Route component={Error} />
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

export default connect(
  mapStateToProps,
  null
)(App);
