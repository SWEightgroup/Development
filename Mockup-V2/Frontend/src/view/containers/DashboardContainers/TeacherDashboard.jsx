import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import { loadDoneExercises } from '../../../actions/ExerciseActions';
import {
  loadClassList,
  getAllStudents
} from '../../../actions/ClassManagementActions';

class TeacherDashboard extends Component {
  constructor(props) {
    super(props);
    const {
      loadDoneExercisesDispatch,
      doneExercises,
      _class,
      loadClassListDispatch,
      getAllStudentsDispatch
    } = props;
    if (!doneExercises.exercises) loadDoneExercisesDispatch();
    if (_class.studentsList < 1) {
      getAllStudentsDispatch();
      loadClassListDispatch();
    }
  }

  render() {
    const { user, doneExercises, _class } = this.props;
    const { firstName, language } = user;
    const { page } = doneExercises;

    const numberOfExercise = page ? page.totalElements : ' ';
    const insertedExercise = (
      <div className="col-md-6 col-xl-4">
        <div className="card mb-3 widget-content bg-midnight-bloom">
          <div className="widget-content-wrapper text-white">
            <div className="widget-content-left">
              <div className="widget-heading">Esercizi inseriti</div>
              <div className="widget-subheading">
                Numero totale di esericizi inseriti
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

    const numberOfClasses =
      _class.studentsList.length > 0 ? _class.classList.length : ' ';
    const classes = (
      <div className="col-md-6 col-xl-4">
        <div className="card mb-3 widget-content bg-arielle-smile">
          <div className="widget-content-wrapper text-white">
            <div className="widget-content-left">
              <div className="widget-heading">Classi</div>
              <div className="widget-subheading">Numero di classi create</div>
            </div>
            <div className="widget-content-right">
              <div className="widget-numbers text-white">
                <span>{numberOfClasses}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    );

    const numberOfStudents = _class.studentsList.length;
    const students = (
      <div className="col-md-6 col-xl-4">
        <div className="card mb-3 widget-content bg-happy-green">
          <div className="widget-content-wrapper text-white">
            <div className="widget-content-left">
              <div className="widget-heading">Studenti</div>
              <div className="widget-subheading">
                Numero di studenti nel sistema
              </div>
            </div>
            <div className="widget-content-right">
              <div className="widget-numbers text-white">
                <span>{numberOfStudents}</span>
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
          {insertedExercise}
          {classes}
          {students}
        </div>
      </React.Fragment>
    );
  }
}

const mapStateToProps = store => {
  return {
    user: store.auth.user,
    doneExercises: store.exercise.doneExercises,
    _class: store.class
  };
};

const mapDispatchToProps = dispatch => {
  return {
    loadDoneExercisesDispatch: link => dispatch(loadDoneExercises(link)),
    loadClassListDispatch: () => dispatch(loadClassList()),
    getAllStudentsDispatch: () => dispatch(getAllStudents())
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TeacherDashboard);
