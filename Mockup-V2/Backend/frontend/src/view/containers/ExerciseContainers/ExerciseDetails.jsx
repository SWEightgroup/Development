import React, { Component } from 'react';
import { connect } from 'react-redux';
import { NavLink } from 'react-router-dom';
import _translator from '../../../helpers/Translator';
import {
  getExerciseDetails,
  initExerciseDetails,
  onDeleteExercise
} from '../../../actions/ExerciseActions';
import SolutionMapper from '../../../helpers/SolutionMapper';
import gerarchy from '../../../constants/gerarchia';

class ExercisesDetails extends Component {
  state = {};

  constructor(props) {
    super(props);
    const {
      doneExercises,
      history,
      match: { params },
      getExerciseDetailsDispatch
    } = this.props;
    this.selectedExercise =
      doneExercises && doneExercises.exercises !== null
        ? doneExercises.exercises.find(
            _exercise => _exercise.id === params.exerciseId
          )
        : null;

    if (!this.selectedExercise) history.push('../assignedHomework');
    getExerciseDetailsDispatch(params.exerciseId);
  }

  componentWillUnmount() {
    const { initExerciseDetailsDispatch } = this.props;
    initExerciseDetailsDispatch();
  }

  deleteExercise = () => {
    const {
      onDeleteExerciseDispatch,
      match: { params },
      history
    } = this.props;
    onDeleteExerciseDispatch(params.exerciseId);
    history.push('../assignedHomework');
  };

  render() {
    const {
      user,
      exerciseDetails,
      match: { params }
    } = this.props;

    if (exerciseDetails && params.exerciseId) {
      const textSplitted = exerciseDetails.exerciseText.split(' ');
      const mainSolution = exerciseDetails.mainSolution
        ? JSON.parse(exerciseDetails.mainSolution.solutionText)
        : [];

      const altSolution = exerciseDetails.alternativeSolution
        ? JSON.parse(exerciseDetails.alternativeSolution.solutionText)
        : [];
      return (
        <React.Fragment>
          <nav aria-label="breadcrumb">
            <ol className="breadcrumb">
              <li className="breadcrumb-item">
                <NavLink to="/assignedHomework" activeClassName="mm-active">
                  {_translator(
                    'sidebarElementTeacher_exercises',
                    user.language
                  )}
                </NavLink>
              </li>
              <li className="breadcrumb-item active" aria-current="page">
                {_translator('detailsTeacher_detailsExercise', user.language)}
              </li>
            </ol>
          </nav>

          <div className="main-card mb-3 card exercise-preview">
            <div className="card-header">
              <button
                type="button"
                className="btn btn-danger pull-right"
                onClick={this.deleteExercise}
              >
                {_translator('gen_delete', user.language)}
              </button>
            </div>
            <div className="card-body">
              <div className="row">
                <div className="col">
                  <h6 className="card-subtitle">
                    {exerciseDetails.authorName}
                  </h6>
                  <h5 className="card-title">
                    <small>{_translator('gen_phrase', user.language)}: </small>
                    {exerciseDetails.exerciseText}
                  </h5>
                </div>
              </div>
            </div>
          </div>

          <div className="main-card mb-3 card exercise-preview">
            <div className="card-header">
              <h6>
                {_translator('executionExercise_mainSolution', user.language)}
              </h6>
            </div>
            <div className="card-body">
              <ul className="list-group list-group-flush">
                {mainSolution.map((word, index) => (
                  <li className="list-group-item" key={`main-${word}-${index}`}>
                    <strong>{textSplitted[index]}</strong> {' : '}
                    {new SolutionMapper(
                      word,
                      gerarchy // [exerciseDetails.language]
                    ).getVerboseSolution(user.language)}
                  </li>
                ))}
              </ul>
            </div>
          </div>

          {exerciseDetails.alternativeSolution && (
            <div className="main-card mb-3 card exercise-preview">
              <div className="card-header">
                <h6>
                  {_translator('executionExercise_altSolution', user.language)}
                </h6>
              </div>
              <div className="card-body">
                <ul className="list-group list-group-flush">
                  {altSolution.map((word, index) => (
                    <li
                      className="list-group-item"
                      key={`alt-${word}-${index}`}
                    >
                      <strong>{textSplitted[index]}</strong> {' : '}
                      {new SolutionMapper(
                        word,
                        gerarchy // [exerciseDetails.language]
                      ).getVerboseSolution(user.language)}
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          )}

          <div className="row">
            <div className="col-6">
              <div className="main-card mb-3 card exercise-preview">
                <div className="card-header">
                  <h6>
                    {_translator('exerciseDetails_studentDone', user.language)}
                  </h6>
                </div>
                <div className="card-body">
                  <ul className="list-group list-group-flush">
                    {exerciseDetails.studentDone.map(student => (
                      <li className="list-group-item" key={`done${student.id}`}>
                        {student.firstName} {student.lastname}
                      </li>
                    ))}
                  </ul>
                </div>
              </div>
            </div>
            <div className="col-6">
              <div className="main-card mb-3 card exercise-preview">
                <div className="card-header">
                  <h6>
                    {_translator('exerciseDetails_studentToDo', user.language)}
                  </h6>
                </div>
                <div className="card-body">
                  <div className="col">
                    <ul className="list-group list-group-flush">
                      {exerciseDetails.studentToDo.map(student => (
                        <li
                          className="list-group-item"
                          key={`todo${student.id}`}
                        >
                          {student.firstName} {student.lastName}
                        </li>
                      ))}
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </React.Fragment>
      );
    }
    return <React.Fragment />;
  }
}
const mapStateToProps = store => {
  return {
    selectedExercise: store.exercise.selectedExercise,
    doneExercises: store.exercise.doneExercises,
    user: store.auth.user,
    exerciseDetails: store.exercise.exerciseDetails
  };
};

const mapDispatchToProps = dispatch => {
  return {
    getExerciseDetailsDispatch: exerciseId =>
      dispatch(getExerciseDetails(exerciseId)),
    initExerciseDetailsDispatch: () => dispatch(initExerciseDetails()),
    onDeleteExerciseDispatch: exerciseId =>
      dispatch(onDeleteExercise(exerciseId))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ExercisesDetails);
