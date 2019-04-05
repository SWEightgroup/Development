import React, { Component } from 'react';
import { connect } from 'react-redux';
import ExercisePreview from '../../components/ExercisePreview';
import {
  updateNewExerciseState,
  initializeNewExercise,
  loadTodoExercises
} from '../../../actions/ExerciseActions';

class Homework extends Component {
  constructor(props) {
    super(props);
    const { todoExercises } = props;
    if (!todoExercises) props.loadTodoExercisesDispatch();
  }

  state = {};

  selectExercise = (phrase, solution, id) => {
    const { updateNewExerciseStateDispatch, newExercise } = this.props;
    const objSolution = JSON.parse(solution);
    console.log('-----', newExercise);
    updateNewExerciseStateDispatch({
      ...newExercise,
      sentenceString: phrase,
      response: objSolution.map(item => {
        return { tag: item };
      }),
      id
    });
    this.props.history.push('homework-execution');
  };

  render() {
    const { todoExercises } = this.props;
    const areThereExerciseToDo = todoExercises && todoExercises.length > 0;
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-center">
          <div className="col-12 col-xs-10 col-md-8 col-xl-6 ">
            {areThereExerciseToDo &&
              todoExercises.map(exercise => (
                <ExercisePreview
                  key={`ex${exercise.id}`}
                  id={exercise.id}
                  author={exercise.teacherName}
                  creationDate={exercise.dateExercise}
                  executionDate={null}
                  phrase={exercise.phraseText}
                  solution={""}
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
    newExercise: store.exercise.newExercise,
    todoExercises: store.exercise.todoExercises
  };
};

const mapDispatchToProps = dispatch => {
  return {
    updateNewExerciseStateDispatch: newExercise =>
      dispatch(updateNewExerciseState(newExercise)),
    initializeNewExerciseDispatch: () => dispatch(initializeNewExercise()),
    loadTodoExercisesDispatch: () => dispatch(loadTodoExercises())
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Homework);
