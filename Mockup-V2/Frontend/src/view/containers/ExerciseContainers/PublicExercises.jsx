import React, { Component } from 'react';
import { connect } from 'react-redux';
import {
  updateNewExerciseState,
  initializeNewExercise,
  loadPublicExercises,
  changePublicExerciseFilter
} from '../../../actions/ExerciseActions';
import _translator from '../../../helpers/Translator';
import { getFavouriteTeachers } from '../../../actions/ClassManagementActions';
import ExercisePreview from '../../components/ExercisePreview';
import Pagination from '../../components/Paginatioin';

class PublicExercises extends Component {
  constructor(props) {
    super(props);
    const { initializeNewExerciseDispatch } = props;
    // if (!todoExercises)
    props.loadPublicExercisesDispatch({ onlyFavourite: false });
    props.getFavouriteTeachersDispatch();
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

  onClickPagination = link => {
    const {
      loadPublicExercisesDispatch,
      onlyFavouritePublicExercise
    } = this.props;
    loadPublicExercisesDispatch({
      _link: link,
      onlyFavourite: onlyFavouritePublicExercise
    });
  };

  render() {
    const {
      publicExercises,
      auth,
      onlyFavouritePublicExercise,
      changePublicExerciseFilterDispatch
    } = this.props;
    const { exercises, page, links } = publicExercises;
    const areThereExercisePublic =
      publicExercises && exercises && exercises.length > 0;

    const classStyleAll = onlyFavouritePublicExercise
      ? 'btn-outline-primary'
      : 'btn-primary';
    const classStyleOnlyFav = !onlyFavouritePublicExercise
      ? 'btn-outline-primary'
      : 'btn-primary';
    return (
      <React.Fragment>
        <div className="row justify-content-center">
          <div className="col-12 col-xs-10 col-md-8 col-xl-6 ">
            <div className="row justify-content-center ">
              <div className="col-6 pr-0 pb-2">
                <button
                  type="button"
                  className={`btn btn-transition btn  btn-lg btn-block rounded-0 ${classStyleAll}`}
                  onClick={() =>
                    changePublicExerciseFilterDispatch(
                      !onlyFavouritePublicExercise
                    )
                  }
                  disabled={!onlyFavouritePublicExercise}
                >
                  {_translator('publicExercises_All', auth.user.language)}
                </button>
              </div>
              <div className="col-6 pl-0">
                <button
                  type="button"
                  onClick={() =>
                    changePublicExerciseFilterDispatch(
                      !onlyFavouritePublicExercise
                    )
                  }
                  className={`btn btn-transition btn  btn-lg btn-block rounded-0 ${classStyleOnlyFav}`}
                  disabled={onlyFavouritePublicExercise}
                >
                  {_translator('publicExercises_Favourite', auth.user.language)}
                </button>
              </div>
            </div>
          </div>
        </div>
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
            onClick={this.onClickPagination}
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
    publicExercises: store.exercise.publicExercises,
    onlyFavouritePublicExercise: store.exercise.onlyFavouritePublicExercise
  };
};

const mapDispatchToProps = dispatch => {
  return {
    updateNewExerciseStateDispatch: newExercise =>
      dispatch(updateNewExerciseState(newExercise)),
    initializeNewExerciseDispatch: () => dispatch(initializeNewExercise()),
    loadPublicExercisesDispatch: ({ _link, onlyFavourite }) =>
      dispatch(loadPublicExercises({ _link, onlyFavourite })),
    changePublicExerciseFilterDispatch: onlyFavourite =>
      dispatch(changePublicExerciseFilter(onlyFavourite)),
    getFavouriteTeachersDispatch: () => dispatch(getFavouriteTeachers())
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PublicExercises);
