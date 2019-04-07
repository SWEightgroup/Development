import React, { Component } from 'react';
import { connect } from 'react-redux';
import ExercisePreview from '../../components/ExercisePreview';
import { loadDoneExercises } from '../../../actions/ExerciseActions';

class DoneHomework extends Component {
  constructor(props) {
    super(props);
    const { doneExercises, loadDoneExercisesDispatch } = props;
    // if (!doneExercises)
    loadDoneExercisesDispatch();
  }

  state = {};

  render() {
    const { doneExercises } = this.props;
    const areThereExerciseDone = doneExercises && doneExercises.length > 0;
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-center">
          <div className="col-12 col-xs-10 col-md-8 col-xl-6 ">
            {areThereExerciseDone &&
              doneExercises.map(exercise => {
                console.log(exercise);
                return (
                  <ExercisePreview
                    key={`ex${exercise.id}`}
                    author={exercise.authorName}
                    creationDate={exercise.dateExercise}
                    executionDate={null}
                    phrase={exercise.phraseText}
                    solution=""
                    mark={null}
                    isMark
                    selectExercise={this.selectExercise}
                  />
                );
              })}
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = store => {
  return {
    auth: store.auth,
    doneExercises: store.exercise.doneExercises
  };
};

const mapDispatchToProps = dispatch => {
  return {
    loadDoneExercisesDispatch: () => dispatch(loadDoneExercises())
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DoneHomework);
