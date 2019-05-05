import React from 'react';

const HelperMessage = ({ children }) => {
  return (
    <div className="row justify-content-md-center">
      <div className="col-sm-12 col-md-8 col-lg-8">
        <div className="main-card mb-3 card">
          <div className="card-body">{children}</div>
        </div>
      </div>
    </div>
  );
};

export default HelperMessage;
