import React, { Component } from 'react';

class Footer extends Component {
  constructor(props) {
    super(props);
    this.addMainJs();
  }

  /**
   * This function adds a script tag to the end of the body tag
   */
  addMainJs = () => {
    const head = document.getElementsByTagName('body')[0];
    const script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = './main.js';
    script.id = 'mainJs';
    if (!document.getElementById('mainJs')) {
      head.appendChild(script);
    }
  };

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
      </div>
    );
  }
}

export default Footer;
