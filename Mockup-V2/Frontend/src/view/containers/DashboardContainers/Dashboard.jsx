import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import {
  loadDoneExercises,
  loadTodoExercises,
  loadPublicExercises
} from '../../../actions/ExerciseActions';

class Dashboard extends Component {
  constructor(props) {
    super(props);
    const {
      loadDoneExercisesDispatch,
      loadTodoExercisesDispatch,
      loadPublicExercisesDispatch,
      doneExercises,
      todoExercises,
      publicExercises
    } = props;
    if (!doneExercises.exercises) loadDoneExercisesDispatch();
    if (!todoExercises.exercises) loadTodoExercisesDispatch();
    if (!publicExercises.exercises) loadPublicExercisesDispatch();
  }

  render() {
    const { user, doneExercises, todoExercises, publicExercises } = this.props;
    const { firstName, language } = user;
    const { exercises } = doneExercises;
    const exercisesToDo = todoExercises.exercises;
    const publics = publicExercises.exercises;

    const numberOfExercise = exercises ? exercises.length : ' ';
    const doneCard = (
      <div className="col-md-6 col-xl-4">
        <div className="card mb-3 widget-content bg-midnight-bloom">
          <div className="widget-content-wrapper text-white">
            <div className="widget-content-left">
              <div className="widget-heading">
                {_translator('dashboard_done', language)}
              </div>
              <div className="widget-subheading">
                {_translator('dashboard_numDone', language)}
              </div>
            </div>
            <div className="widget-content-right">
              <div className="widget-numbers text-white">
                <span>{numberOfExercise}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    );

    const numberOfToDo = exercisesToDo ? exercisesToDo.length : ' ';
    const toDoCard = (
      <div className="col-md-6 col-xl-4">
        <div className="card mb-3 widget-content bg-arielle-smile">
          <div className="widget-content-wrapper text-white">
            <div className="widget-content-left">
              <div className="widget-heading">
                {_translator('dashboard_toDo', language)}
              </div>
              <div className="widget-subheading">
                {_translator('dashboard_numToDo', language)}
              </div>
            </div>
            <div className="widget-content-right">
              <div className="widget-numbers text-white">
                <span>{numberOfToDo}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    );

    const numberOfPublics = publics ? publics.length : 0;
    const publicExCard = (
      <div className="col-md-6 col-xl-4">
        <div className="card mb-3 widget-content bg-happy-green">
          <div className="widget-content-wrapper text-white">
            <div className="widget-content-left">
              <div className="widget-heading">
                {_translator('dashboard_public', language)}
              </div>
              <div className="widget-subheading">
                {_translator('dashboard_numPublic', language)}
              </div>
            </div>
            <div className="widget-content-right">
              <div className="widget-numbers text-white">
                <span>{numberOfPublics}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    );

    const percent =
      (numberOfExercise / (numberOfToDo + numberOfExercise)) * 100;
    const percentToShow = Number.isNaN(percent) ? 0 : percent.toFixed(2);
    const donePercentage = (
      <div className="col-md-8">
        <div className="card mb-3 widget-content">
          <div className="widget-content-outer">
            <div className="widget-content-wrapper">
              <div className="widget-content-left">
                <div className="widget-heading">
                  {_translator('dashboard_donePercentTitle', language)}
                </div>
                <div className="widget-subheading">
                  {_translator('dashboard_donePercent', language)}
                </div>
              </div>
              <div className="widget-content-right">
                <div className="widget-numbers text-success">
                  {percentToShow}%
                </div>
              </div>
            </div>
            <div className="widget-progress-wrapper">
              <div className="progress-bar-xs progress">
                <div
                  className="progress-bar bg-primary"
                  role="progressbar"
                  aria-valuenow={percentToShow}
                  aria-valuemin="0"
                  aria-valuemax="100"
                  style={{ width: `${percentToShow}%` }}
                />
              </div>
              <div className="progress-sub-label">
                <div className="sub-label-right">100%</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );

    return (
      <React.Fragment>
        <div className="row justify-content-center">
          <div className="py-5 text-center">
            <h2>{_translator('gen_userDashboard', language)}</h2>

            <p className="lead">
              {_translator('dashboard_hiUser', language)}
              {firstName}
            </p>
          </div>
        </div>
        <div className="row justify-content-center">
          {doneCard}
          {toDoCard}
          {publicExCard}
        </div>
        <div className="row justify-content-center pt-5">{donePercentage}</div>
      </React.Fragment>
    );
  }
}

const mapStateToProps = store => {
  return {
    user: store.auth.user,
    doneExercises: store.exercise.doneExercises,
    todoExercises: store.exercise.todoExercises,
    publicExercises: store.exercise.publicExercises
  };
};

const mapDispatchToProps = dispatch => {
  return {
    loadDoneExercisesDispatch: link => dispatch(loadDoneExercises(link)),
    loadTodoExercisesDispatch: link => dispatch(loadTodoExercises(link)),
    loadPublicExercisesDispatch: link => dispatch(loadPublicExercises(link))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Dashboard);
