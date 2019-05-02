import React, { Component } from 'react';
import { connect } from 'react-redux';
import InputSentence from '../../components/InputSentence';
import ExecutionExercise from '../../components/ExecutionExercise';
import _translator from '../../../helpers/Translator';
import {
  initializeNewExercise,
  updateNewExerciseState,
  changeNewInputSentence,
  saveExerciseSolution,
  initNewExerciseState,
  innerLoaderOff,
  innerLoaderOn,
  getAutomaticSolution
} from '../../../actions/ExerciseActions';

import {
  getAllStudents,
  updateStudentsList,
  loadClassList
} from '../../../actions/ClassManagementActions';

class InsertExercise extends Component {
  constructor(props) {
    super(props);
    props.initializeNewExercise();
    props.getAllStudentsDispatch();
    props.loadClassListDispatch();
  }

  /**
   * split the sentence
   *
   * @param sentence the sentence to analyze
   */
  prepareExercise = sentenceString => {
    const now = Date.now();
    const { newExercise } = this.props;
    const sentenceArray = sentenceString.split(' ');
    this.getSolution({
      ...newExercise,
      showSolution: false,
      showButton: false,
      response: null,
      createAt: now,
      sentence: sentenceArray,
      assignedUsersIds: []
    });
  };

  changeNewInputSentence = input => {
    const { changeNewInputSentenceDispatch, newExercise } = this.props;
    changeNewInputSentenceDispatch({
      ...newExercise,
      sentenceString: input
    });
  };

  /**
   * set the showSolution flag to true
   */
  checkSolution = () => {
    const {
      newExercise,
      saveExerciseSolutionDispatch,
      updateNewExerciseStateDispatch
    } = this.props;

    const codeSolution1 = newExercise.userSolution[0].map((word, index) => {
      if (word.languageIterator.getSolution().length === 0) {
        return newExercise.response[index].tag;
      }
      return word.languageIterator.getCodeSolution();
    }); // questo è un array di codici che invio al backend

    const codeSolution2 = newExercise.alternativeSolution
      ? newExercise.userSolution[1].map((word, index) => {
          if (word.languageIterator.getSolution().length === 0) {
            return newExercise.response[index].tag;
          }
          return word.languageIterator.getCodeSolution();
        })
      : newExercise.userSolution[1]; // questo è un array di codici che invio al backend

    updateNewExerciseStateDispatch({
      ...newExercise,
      showSolution: true
    });
    saveExerciseSolutionDispatch({
      ...newExercise,
      showSolution: true,
      codeSolution: [codeSolution1, codeSolution2]
    });
  };

  /**
   * call the server to analyze the sentence
   */
  getSolution = newExercise => {
    const {
      initNewExerciseStateDispatch,
      getAutomaticSolutionDispatch
    } = this.props;

    this.props.innerLoaderOn();
    initNewExerciseStateDispatch({
      ...newExercise,
      response: null
    });
    const { sentenceString } = newExercise;

    const toSend = sentenceString.replace('.', ' ');

    getAutomaticSolutionDispatch(toSend);
  };

  handleChange = event => {
    const { updateStudentsListDispatch, studentsList } = this.props;

    studentsList.find(student => student.username === event.target.id).check =
      event.target.checked;
    updateStudentsListDispatch(studentsList);
  };

  /**
   *  Select/Deselect students of this class
   *  @param event Event
   *  @param students List of students id
   */
  handleChangeClassList = (event, students) => {
    const { updateStudentsListDispatch, studentsList } = this.props;
    if (students && students.length > 0) {
      students.forEach(student => {
        const element = studentsList.find(
          _student => _student.id === student.id
        );
        if (element !== undefined) {
          element.check = event.target.checked;
        }
      });
    }
    updateStudentsListDispatch(studentsList);
  };

  render() {
    const {
      changeNewInputSentenceDispatch,
      newExercise,
      auth,
      studentsList,
      classList,
      updateNewExerciseStateDispatch
    } = this.props;
    console.log(': InsertExercise -> render -> classList', classList);
    const { user } = auth;
    const {
      sentence,
      response,
      showSolution,
      createAt,
      sentenceString,
      showButton,
      alternativeSolution
    } = newExercise;

    const { language } = user;
    return (
      <React.Fragment>
        <InputSentence
          prepareExercise={this.prepareExercise}
          changeNewInputSentence={changeNewInputSentenceDispatch}
          sentenceString={sentenceString}
          language={language}
        />
        {response && (
          <React.Fragment>
            <h2 className="h5">
              {_translator('executionExercise_mainSolution', language)}
            </h2>
            <ExecutionExercise
              sentence={sentence} // array di parole
              response={response}
              showSolution={showSolution}
              createAt={createAt}
              salvaEsercizio={this.salvaEsercizio}
              language={language}
              initSolution
              showButton={showButton}
              indexSolution={0}
            />
            <div className="main-card  card no-bg-color ">
              <div className="card-body pt-0 pl-1">
                {!alternativeSolution && !showSolution && (
                  <button
                    type="button"
                    className="btn btn-success btn-lg "
                    onClick={() =>
                      updateNewExerciseStateDispatch({
                        ...newExercise,
                        alternativeSolution: true
                      })
                    }
                  >
                    {_translator('executionExercise_addSolAlter', language)}
                  </button>
                )}
                {alternativeSolution && !showSolution && (
                  <button
                    type="button"
                    className="btn btn-danger btn-lg "
                    onClick={() =>
                      updateNewExerciseStateDispatch({
                        ...newExercise,
                        alternativeSolution: false
                      })
                    }
                  >
                    {_translator('executionExercise_delSolAlter', language)}
                  </button>
                )}
              </div>
            </div>
            {alternativeSolution && (
              <React.Fragment>
                <h2 className="h5">
                  {_translator('executionExercise_altSolution', language)}
                </h2>
                <ExecutionExercise
                  sentence={sentence} // array di parole
                  response={response}
                  showSolution={showSolution}
                  createAt={createAt}
                  salvaEsercizio={this.salvaEsercizio}
                  language={language}
                  initSolution
                  showButton={showButton}
                  indexSolution={1}
                />
              </React.Fragment>
            )}
          </React.Fragment>
        )}

        {response && studentsList && !showSolution && studentsList.length > 0 && (
          <div className="row">
            <div className="col-sm-12 col-md-6 ">
              <div className="mb-3 card">
                <div className="card-header-tab card-header-tab-animation card-header">
                  <div className="card-header-title">
                    <i className="header-icon lnr-apartment icon-gradient bg-love-kiss" />
                    {_translator('insertExercise_StudentDisp', language)}
                  </div>
                </div>
                <div className="card-body">
                  <div className="tab-content">
                    <div className="tab-pane fade show active">
                      <div className="scroll-area-sm">
                        <div className="scrollbar-container">
                          <ul className="rm-list-borders rm-list-borders-scroll list-group list-group-flush">
                            {studentsList.map(student => (
                              <li
                                key={`li-${student.username}`}
                                className="list-group-item"
                              >
                                <div className="widget-content p-0">
                                  <div className="widget-content-wrapper">
                                    <div className="widget-content-left mr-3">
                                      <input
                                        type="checkbox"
                                        value={student.username}
                                        id={student.username}
                                        onChange={this.handleChange}
                                        checked={
                                          student.check ? student.check : false
                                        }
                                      />
                                    </div>

                                    <div className="widget-content-left">
                                      <label htmlFor={student.username}>
                                        <div className="widget-heading">
                                          {student.lastName} {student.firstName}
                                        </div>
                                        <div className="widget-subheading">
                                          {student.username}
                                        </div>
                                      </label>
                                    </div>
                                  </div>
                                </div>
                              </li>
                            ))}
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            {classList && classList.length > 0 && (
              <div className="col-sm-12 col-md-6 ">
                <div className="mb-3 card">
                  <div className="card-header-tab card-header-tab-animation card-header">
                    <div className="card-header-title">
                      <i className="header-icon lnr-apartment icon-gradient bg-love-kiss" />
                      {_translator('insertExercise_ClassDisp', language)}
                    </div>
                  </div>
                  <div className="card-body">
                    <div className="tab-content">
                      <div className="tab-pane fade show active">
                        <div className="scroll-area-sm">
                          <div className="scrollbar-container">
                            <ul className="rm-list-borders rm-list-borders-scroll list-group list-group-flush">
                              {classList.map(classElement => (
                                <li
                                  key={`li-${classElement.classId}`}
                                  className="list-group-item"
                                >
                                  <div className="widget-content p-0">
                                    <div className="widget-content-wrapper">
                                      <div className="widget-content-left mr-3">
                                        <input
                                          type="checkbox"
                                          id={classElement.classId}
                                          name={classElement.className}
                                          value={classElement}
                                          onChange={e =>
                                            this.handleChangeClassList(
                                              e,
                                              classElement.students
                                            )
                                          }
                                        />
                                      </div>
                                      <div className="widget-content-left">
                                        <label htmlFor={classElement.classId}>
                                          <div className="widget-heading">
                                            {classElement.className}
                                          </div>
                                        </label>
                                      </div>
                                    </div>
                                  </div>
                                </li>
                              ))}
                            </ul>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            )}
          </div>
        )}
        {response && (
          <div className="main-card mb-3 card no-bg-color ">
            <div className="card-body">
              <button
                type="button"
                className="btn btn-success btn-lg btn-block"
                onClick={this.checkSolution}
                disabled={showSolution}
              >
                {_translator('executionExercise_complete', language)}
              </button>
            </div>
          </div>
        )}
      </React.Fragment>
    );
  }
}
const mapStateToProps = store => {
  return {
    authError: store.auth.authError,
    auth: store.auth,
    token: store.auth.user.token,
    newExercise: store.exercise.newExercise,
    studentsList: store.class.studentsList,
    classList: store.class.classList
  };
};

const mapDispatchToProps = dispatch => {
  return {
    loadClassListDispatch: () => dispatch(loadClassList()),
    initializeNewExercise: () => dispatch(initializeNewExercise()),
    updateNewExerciseStateDispatch: newExercise =>
      dispatch(updateNewExerciseState(newExercise)),
    changeNewInputSentenceDispatch: input =>
      dispatch(changeNewInputSentence(input)),
    saveExerciseSolutionDispatch: newExercise =>
      dispatch(saveExerciseSolution(newExercise)),
    initNewExerciseStateDispatch: newExercise =>
      dispatch(initNewExerciseState(newExercise)),
    innerLoaderOff: () => dispatch(innerLoaderOff()),
    innerLoaderOn: () => dispatch(innerLoaderOn()),
    getAutomaticSolutionDispatch: sentenceString =>
      dispatch(getAutomaticSolution(sentenceString)),
    getAllStudentsDispatch: () => dispatch(getAllStudents()),
    updateStudentsListDispatch: studentsList =>
      dispatch(updateStudentsList(studentsList))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InsertExercise);
