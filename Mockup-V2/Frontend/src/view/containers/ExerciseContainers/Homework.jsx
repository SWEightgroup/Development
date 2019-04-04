import React, { Component } from 'react';
import { connect } from 'react-redux';
import ExercisePreview from '../../components/ExercisePreview';
import { updateNewExerciseState } from '../../../actions/ExerciseActions';

class Homework extends Component {
  state = {};

  selectExercise = (phrase, solution) => {
    const { updateNewExerciseStateDispatch, newExercise } = this.props;
    const objSolution = JSON.parse(solution);
    updateNewExerciseStateDispatch({
      ...newExercise,
      sentenceString: phrase,
      response: objSolution.map(item => {
        return { tag: item };
      })
    });
  };

  render() {
    const { auth } = this.props;
    const { user } = auth;
    const { exercisesToDo } = user;
    const areThereExerciseToDo = exercisesToDo && exercisesToDo.length > 0;
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-center">
          <div className="col-12 col-xs-10 col-md-8 col-xl-6 ">
            {areThereExerciseToDo &&
              exercisesToDo.map(exercise => (
                <ExercisePreview
                  key={`ex${exercise.id}`}
                  author={exercise.teacherName}
                  creationDate={exercise.dateExercise}
                  executionDate={null}
                  phrase={exercise.phraseText}
                  solution={exercise.mainSolutionReference.solutionText}
                  mark={null}
                  isMark={false}
                  selectExercise={this.selectExercise}
                />
              ))}
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = store => {
  return {
    auth: store.auth,
    newExercise: store.exercise.newExercise
  };
};

const mapDispatchToProps = dispatch => {
  return {
    updateNewExerciseStateDispatch: newExercise =>
      dispatch(updateNewExerciseState(newExercise))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Homework);
