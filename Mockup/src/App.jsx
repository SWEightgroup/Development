import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";
import Navbar from "./components/header/Navbar";
import Sidebar from "./components/sidebar/Sidebar";
import Body from "./components/body/Body";
import Footer from "./components/footer/Footer";

class App extends Component {
  render() {
    return (
      <div className="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
        <Navbar />
        <div className="app-main">
          <div className="app-sidebar sidebar-shadow">
            <div className="app-header__logo">
              <div className="logo-src" />
              <div className="header__pane ml-auto">
                <div>
                  <button type="button" className="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                    <span className="hamburger-box">
                      <span className="hamburger-inner" />
                    </span>
                  </button>
                </div>
              </div>
            </div>
            <div className="app-header__mobile-menu">
              <div>
                <button type="button" className="hamburger hamburger--elastic mobile-toggle-nav">
                  <span className="hamburger-box">
                    <span className="hamburger-inner" />
                  </span>
                </button>
              </div>
            </div>
            <Sidebar />
          </div>
          <div className="app-main__outer">
            <Body />
            <Footer />
          </div>
        </div>


      </div>
    );
  }
}

export default App;
