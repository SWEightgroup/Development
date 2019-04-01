import _translator from '../../helpers/Translator';
import React, { Component } from 'react';

export default class Error extends Component {
  render() {
    const { language } = this.props;
    return (
      <div className="app-main__inner ">
        <div className="row justify-content-md-center">
          <div className="col-sm-12 col-md-6">
            <h1>{_translator('gen_404Error', language)}</h1>
          </div>
        </div>
      </div>
    );
  }
}
