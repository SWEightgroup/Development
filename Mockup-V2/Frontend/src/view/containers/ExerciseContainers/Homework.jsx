import React, { Component } from 'react';
import ExercisePreview from '../../components/ExercisePreview';
class Homework extends Component {
  state = {};
  render() {
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-center">
          <div className="col-12 col-xs-10 col-md-8 col-xl-6 ">
            <ExercisePreview
              author="Magarotto Francesco"
              creationDate={new Date()}
              executionDate={new Date()}
              phrase="I topi non avevano nipoti"
              mark="5"
              isMark={true}
            />

            <ExercisePreview
              author="Magarotto Francesco"
              creationDate={new Date()}
              executionDate={new Date()}
              phrase="I topi non avevano nipoti"
              isMark={false}
            />
          </div>
        </div>
      </div>
    );
  }
}

export default Homework;
