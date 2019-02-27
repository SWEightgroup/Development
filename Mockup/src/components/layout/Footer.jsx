import React, { Component } from 'react';
import { Helmet } from 'react-helmet';
class Footer extends Component {
  state = {};

  render() {
    return (
      <div className="app-wrapper-footer">
        <div className="app-footer">
          <div className="app-footer__inner">
            <div className="app-footer-left">
              <ul className="nav">
                <li className="nav-item">
                  <a href="/" className="nav-link">
                    Footer Link 1
                  </a>
                </li>
                <li className="nav-item">
                  <a href="/" className="nav-link">
                    Footer Link 2
                  </a>
                </li>
              </ul>
            </div>
            <div className="app-footer-right">
              <ul className="nav">
                <li className="nav-item">
                  <a href="/" className="nav-link">
                    Footer Link 3
                  </a>
                </li>
                <li className="nav-item">
                  <a href="/" className="nav-link">
                    <div className="badge badge-success mr-1 ml-0">
                      <small>NEW</small>
                    </div>
                    Footer Link 4
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>

        {
          // this component injects, after the parent component is loaded, a script tag
        }
        <Helmet>
          <script type="text/javascript" src="./main.js" />
        </Helmet>
      </div>
    );
  }
}

export default Footer;
