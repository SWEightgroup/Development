import React, { Component } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import { connect } from 'react-redux';
import Navbar from './components/layout/Navbar';
import Sidebar from './components/layout/Sidebar';
import Body from './components/layout/Body';
import Footer from './components/layout/Footer';
import SignUp from './components/auth/SignUp';
import SignIn from './components/auth/SignIn';
import Error from './components/layout/Error';
import { loadAuth } from './store/actions/AuthActions';
import './App.css';

class App extends Component {
  render() {
    const { loader } = this.props;
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
              <Sidebar />
            </div>
            <div className="app-main__outer">
              <Switch>
                <Route exact path="/" component={Body} />
                <Route path="/signin" component={SignIn} />
                <Route path="/signup" component={SignUp} />
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

const mapStateToProps = state => {
  return {
    loader: state.auth.loader
  };
};
const mapDispatchToProps = dispatch => {
  return {
    loadAuth: () => dispatch(loadAuth())
  };
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(App);
