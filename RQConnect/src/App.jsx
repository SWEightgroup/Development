import React, { Component } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

import Navbar from './components/layout/Navbar';
import Dashboard from './components/dashboard/Dashboard';
import IssueDetails from './components/issues/IssueDetails';
import SignIn from './components/auth/SignIn';
import SignUp from './components/auth/SignUp';
import CreateIssue from './components/issues/CreateIssue';
import Profile from './components/profile/profile';
import Error from './components/layout/Error';
import 'materialize-css/dist/css/materialize.min.css';
import Footer from './components/layout/Footer';
class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <React.Fragment>
          <Navbar />
          <main>
            <Switch>
              <Route exact path="/" component={Dashboard} />
              <Route path="/issue/:id" component={IssueDetails} />
              <Route path="/signin" component={SignIn} />
              <Route path="/signup" component={SignUp} />
              <Route path="/create" component={CreateIssue} />
              <Route path="/profile" component={Profile} />
              <Route component={Error} />
            </Switch>
          </main>
          <Footer />
        </React.Fragment>
      </BrowserRouter>
    );
  }
}

export default App;
