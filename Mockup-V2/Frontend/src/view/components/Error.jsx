import React from 'react';
import _translator from './Translator';

const Error = () => {
  return (
    <div className="app-main__inner ">
      <div className="row justify-content-md-center">
        <div className="col-sm-12 col-md-6">
          <h1>{_translator('gen_404Error')}</h1>
        </div>
      </div>
    </div>
  );
};

export default Error;
