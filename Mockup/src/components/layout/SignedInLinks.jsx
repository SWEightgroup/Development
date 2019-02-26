import React, { Component } from 'react';

class SignedInLinks extends Component {
  state = {};

  render() {
    return (
      <React.Fragment>
        <div className="app-header-left">
          <div className="search-wrapper">
            <div className="input-holder">
              <input
                type="text"
                className="search-input"
                placeholder="Type to search"
              />
              <button type="button" className="search-icon">
                <span />
              </button>
            </div>
            <button type="button" className="close" />
          </div>
          <ul className="header-menu nav">
            <li className="nav-item">
              <a href="" className="nav-link">
                <i className="nav-link-icon fa fa-database" />
                Statistics
              </a>
            </li>
            <li className="btn-group nav-item">
              <a href="" className="nav-link">
                <i className="nav-link-icon fa fa-edit" />
                Projects
              </a>
            </li>
            <li className="dropdown nav-item">
              <a href="" className="nav-link">
                <i className="nav-link-icon fa fa-cog" />
                Settings
              </a>
            </li>
          </ul>
        </div>

        {/* ---------------------------------------------------------------------------*/}

        <div className="app-header-right">
          <div className="header-btn-lg pr-0">
            <div className="widget-content p-0">
              <div className="widget-content-wrapper">
                <div className="widget-content-left">
                  <div className="btn-group">
                    <a
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                      className="p-0 btn"
                      href=""
                    >
                      <img
                        width="42"
                        className="rounded-circle"
                        src="assets/images/avatars/1.jpg"
                        alt=""
                      />
                      <i className="fa fa-angle-down ml-2 opacity-8" />
                    </a>
                    <div
                      tabIndex="-1"
                      role="menu"
                      aria-hidden="true"
                      className="dropdown-menu dropdown-menu-right"
                    >
                      <button
                        type="button"
                        tabIndex="0"
                        className="dropdown-item"
                      >
                        User Account
                      </button>
                      <button
                        type="button"
                        tabIndex="0"
                        className="dropdown-item"
                      >
                        Settings
                      </button>
                      <h6 tabIndex="-1" className="dropdown-header">
                        Header
                      </h6>
                      <button
                        type="button"
                        tabIndex="0"
                        className="dropdown-item"
                      >
                        Actions
                      </button>
                      <div tabIndex="-1" className="dropdown-divider" />
                      <button
                        type="button"
                        tabIndex="0"
                        className="dropdown-item"
                      >
                        Dividers
                      </button>
                    </div>
                  </div>
                </div>
                <div className="widget-content-left  ml-3 header-user-info">
                  <div className="widget-heading">Alina Mclourd</div>
                  <div className="widget-subheading">VP People Manager</div>
                </div>
                <div className="widget-content-right header-user-info ml-3" />
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default SignedInLinks;
