import React, { Component } from 'react';
import { connect } from 'react-redux';
import { NavLink, withRouter } from 'react-router-dom';

class Sidebar extends Component {
  render() {
    const { auth } = this.props;
    if (!(auth && auth.user)) return null;
    const userClass = 'student';
    const NavLinkstoRender = new Array();
    NavLinkstoRender.push(
      this.navMaker('/exercise', 'pe-7s-pen', 'Nuovo Esercizio')
    );
    if (userClass === 'student')
      NavLinkstoRender.push(
        this.navMaker('/dashboard', 'pe-7s-user', 'Pannello Utente')
      );
    return (
      <div className="scrollbar-sidebar">
        <div className="app-sidebar__inner">
          <ul className="vertical-nav-menu">
            <li className="app-sidebar__heading">Dashboards</li>
            <li>
              {NavLinkstoRender.map((entry, index) => (
                <NavLink
                  to={entry.to}
                  className="nav-link"
                  activeClassName="mm-active"
                  key={index}
                >
                  <i className={'metismenu-icon ' + entry.icon} />
                  {entry.label}
                </NavLink>
              ))}
            </li>
          </ul>
        </div>
      </div>
    );
  }

  navMaker(to, icon, label) {
    return {
      to,
      icon,
      label
    };
  }
}

const mapStateToProps = store => {
  return {
    auth: store.auth
  };
};

export default withRouter(connect(mapStateToProps)(Sidebar));
