import React, { Component } from 'react';
import { connect } from 'react-redux';
import { NavLink } from 'react-router-dom';
import _translator from '../../../helpers/Translator';

class ExercisesDetails extends Component {
  state = {};

  constructor(props) {
    super(props);
    const {
      doneExercises,
      history,
      match: { params }
    } = this.props;
    this.selectedExercise =
      doneExercises && doneExercises.exercises !== null
        ? doneExercises.exercises.find(
            _exercise => _exercise.id === params.exerciseId
          )
        : null;

    if (!this.selectedExercise) history.push('../assignedHomework');
  }

  render() {
    const { user } = this.props;
    if (this.selectedExercise) {
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
            <div className="card-body">
              <div className="row">
                <div className="col">
                  <h5 className="card-title">
                    {' '}
                    {this.selectedExercise.phraseText}
                  </h5>
                  <h6 className="card-subtitle">
                    {this.selectedExercise.authorName}
                  </h6>
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
    user: store.auth.user
  };
};

export default connect(mapStateToProps)(ExercisesDetails);
