import React from "react";
import { NavLink } from "react-router-dom";

const SignedOutLinks = () => {
  return (
    <React.Fragment>
      <li>
        <NavLink to="/signup" className="sidenav-close blue-text">
          Registati
        </NavLink>
      </li>
      <li>
        <NavLink to="/signin" className="sidenav-close">
          Accedi
        </NavLink>
      </li>
    </React.Fragment>
  );
};

export default SignedOutLinks;
