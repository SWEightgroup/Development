import React, { Component } from 'react';
import { connect } from 'react-redux';
import {
  updateNewExerciseState,
  initializeNewExercise,
  loadTodoExercises
} from '../../../actions/ExerciseActions';
import ExercisePreview from '../../components/ExercisePreview';
import Pagination from '../../components/Paginatioin';

class Homework extends Component {
  constructor(props) {
    super(props);
    const { initializeNewExerciseDispatch } = props;
    // if (!todoExercises)
    props.loadTodoExercisesDispatch();
    initializeNewExerciseDispatch();
  }

  state = {};

  selectExercise = (phrase, id) => {
    const { updateNewExerciseStateDispatch, newExercise, history } = this.props;
    updateNewExerciseStateDispatch({
      ...newExercise,
      sentenceString: phrase,
      id
    });
    history.push('homework-execution');
  };

  render() {
    const { todoExercises, auth, loadTodoExercisesDispatch } = this.props;
    const { exercises, page, links } = todoExercises;
    const areThereExerciseToDo =
      todoExercises && exercises && exercises.length > 0;
    return (
      <React.Fragment>
        <div className="row justify-content-center">
          <div className="col-12 col-xs-10 col-md-8 col-xl-6 ">
            {areThereExerciseToDo &&
              exercises &&
              exercises.map((exercise, index) => (
                <ExercisePreview
                  key={`ex${exercise.id}${index}`}
                  id={exercise.id}
                  author={exercise.authorName}
                  creationDate={exercise.dateExercise}
                  executionDate={null}
                  phrase={exercise.phraseText}
                  solution=""
                  mark={null}
                  isMark={false}
                  selectExercise={this.selectExercise}
                  language={auth.user.language}
                />
              ))}
          </div>
        </div>
        {areThereExerciseToDo && (
          <Pagination
            first={links.first}
            last={links.last}
            next={links.next}
            prev={links.prev}
            number={page.number}
            totalPages={page.totalPages}
            language={auth.user.language}
            onClick={loadTodoExercisesDispatch}
          />
        )}
      </React.Fragment>
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
    loadTodoExercisesDispatch: link => dispatch(loadTodoExercises(link))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Homework);
