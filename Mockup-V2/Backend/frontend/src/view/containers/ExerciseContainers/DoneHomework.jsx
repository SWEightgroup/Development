import React, { Component } from 'react';
import { connect } from 'react-redux';
import ExercisePreview from '../../components/ExercisePreview';
import { loadDoneExercises } from '../../../actions/ExerciseActions';
import Pagination from '../../components/Paginatioin';

class DoneHomework extends Component {
  constructor(props) {
    super(props);
    const { loadDoneExercisesDispatch } = props;
    // if (!doneExercises)
    loadDoneExercisesDispatch();
  }

  state = {};

  render() {
    const { doneExercises, auth, loadDoneExercisesDispatch } = this.props;
    const { exercises, page, links } = doneExercises;
    const areThereExerciseDone =
      exercises && doneExercises.exercises.length > 0;
    const { language } = auth.user;
    return (
      <React.Fragment>
        <div className="row justify-content-center">
          <div className="col-12 col-xs-10 col-md-8 col-xl-6 ">
            {areThereExerciseDone &&
              doneExercises.exercises.map((exercise, index) => {
                return (
                  <ExercisePreview
                    key={`ex-${exercise.id}${index}`}
                    author={exercise.authorName}
                    creationDate={exercise.dateExercise}
                    executionDate={null}
                    phrase={exercise.phraseText}
                    solution=""
                    mark={null}
                    isMark
                    selectExercise={this.selectExercise}
                    language={language}
                  />
                );
              })}
          </div>
        </div>
        {areThereExerciseDone && (
          <Pagination
            first={links.first}
            last={links.last}
            next={links.next}
            prev={links.prev}
            number={page.number}
            totalPages={page.totalPages}
            language={language}
            onClick={loadDoneExercisesDispatch}
          />
        )}
      </React.Fragment>
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
    loadDoneExercisesDispatch: link => dispatch(loadDoneExercises(link))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DoneHomework);
