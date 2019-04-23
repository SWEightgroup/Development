import React, { Component } from 'react';
import { connect } from 'react-redux';
import {
  updateNewExerciseState,
  initializeNewExercise,
  loadPublicExercises
} from '../../../actions/ExerciseActions';
import ExercisePreview from '../../components/ExercisePreview';
import Pagination from '../../components/Paginatioin';

class PublicExercises extends Component {
  constructor(props) {
    super(props);
    const { initializeNewExerciseDispatch } = props;
    // if (!todoExercises)
    props.loadPublicExercisesDispatch();
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
    const { publicExercises, auth, loadPublicExercisesDispatch } = this.props;
    const { exercises, page, links } = publicExercises;
    const areThereExercisePublic =
      publicExercises && exercises && exercises.length > 0;
    return (
      <React.Fragment>
        <div className="row justify-content-center">
          <div className="col-12 col-xs-10 col-md-8 col-xl-6 ">
            {areThereExercisePublic &&
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
        {areThereExercisePublic && (
          <Pagination
            first={links.first}
            last={links.last}
            next={links.next}
            prev={links.prev}
            number={page.number}
            totalPages={page.totalPages}
            language={auth.user.language}
            onClick={loadPublicExercisesDispatch}
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
    publicExercises: store.exercise.publicExercises
  };
};

const mapDispatchToProps = dispatch => {
  return {
    updateNewExerciseStateDispatch: newExercise =>
      dispatch(updateNewExerciseState(newExercise)),
    initializeNewExerciseDispatch: () => dispatch(initializeNewExercise()),
    loadPublicExercisesDispatch: link => dispatch(loadPublicExercises(link))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PublicExercises);
