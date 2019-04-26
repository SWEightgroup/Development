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

    const codeSolution = newExercise.userSolution.map((word, index) => {
      if (word.languageIterator.getSolution().length === 0) {
        return newExercise.response[index].tag;
      }
      return word.languageIterator.getCodeSolution();
    }); // questo è un array di codici che invio al backend
    updateNewExerciseStateDispatch({
      ...newExercise,
      showSolution: true
    });
    saveExerciseSolutionDispatch({
      ...newExercise,
      showSolution: true,
      codeSolution
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
    getAutomaticSolutionDispatch(sentenceString);
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
   *  @param studentsId List of students id
   */
  handleChangeClassList = (event, studentsId) => {
    const { updateStudentsListDispatch, studentsList } = this.props;
    if (studentsId && studentsId.length > 0) {
      studentsId.forEach(studentId => {
        const element = studentsList.find(student => student.id === studentId);
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
      classList
    } = this.props;
    const { user } = auth;
    const {
      sentence,
      response,
      showSolution,
      createAt,
      sentenceString,
      showButton
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
          <ExecutionExercise
            sentence={sentence} // array di parole
            response={response}
            showSolution={showSolution}
            createAt={createAt}
            salvaEsercizio={this.salvaEsercizio}
            language={language}
            initSolution
            showButton={showButton}
          />
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
                                  key={`li-${classElement.id}`}
                                  className="list-group-item"
                                >
                                  <div className="widget-content p-0">
                                    <div className="widget-content-wrapper">
                                      <div className="widget-content-left mr-3">
                                        <input
                                          type="checkbox"
                                          id={classElement.id}
                                          name={classElement.id}
                                          value={classElement}
                                          onChange={e =>
                                            this.handleChangeClassList(
                                              e,
                                              classElement.studentsId
                                            )
                                          }
                                        />
                                      </div>
                                      <div className="widget-content-left">
                                        <label htmlFor={classElement.id}>
                                          <div className="widget-heading">
                                            {classElement.name}
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

/* 

"   { "sentences" : [
      { "id":"1",
        "tokens" : [
           { "id" : "t1.1", "begin" : "0", "end" : "10", "form" : "sebastiano", "lemma" : "sebastiano", "tag" : "AQ0MS00", "ctag" : "AQ", "pos" : "adjective", "type" : "qualificative", "gen" : "masculine", "num" : "singular"},
           { "id" : "t1.2", "begin" : "11", "end" : "18", "form" : "caccaro", "lemma" : "caccaro", "tag" : "AQ0MS00", "ctag" : "AQ", "pos" : "adjective", "type" : "qualificative", "gen" : "masculine", "num" : "singular"},
           { "id" : "t1.3", "begin" : "19", "end" : "27", "form" : "facebook", "lemma" : "facebook", "tag" : "NCMN000", "ctag" : "NC", "pos" : "noun", "type" : "common", "gen" : "masculine", "num" : "invariable"},
           { "id" : "t1.4", "begin" : "28", "end" : "29", "form" : ".", "lemma" : ".", "tag" : "Fp", "ctag" : "Fp", "pos" : "punctuation", "type" : "period"}]}]}
" 
"{ "sentences" : [
      { "id":"1",
        "tokens" : [
           { "id" : "t1.1", "begin" : "0", "end" : "1", "form" : "{", "lemma" : "{", "tag" : "Fla", "ctag" : "Fla", "pos" : "punctuation", "type" : "curlybracket", "punctenclose" : "open"},
           { "id" : "t1.2", "begin" : "1", "end" : "2", "form" : ""\", "lemma" : "\"", "tag" : "Fe", "ctag" : "Fe", "pos" : "punctuation", "type" : "quotation"},
           { "id" : "t1.3", "begin" : "2", "end" : "6", "form" : "text", "lemma" : "text", "tag" : "NCMN000", "ctag" : "NC", "pos" : "noun", "type" : "common", "gen" : "masculine", "num" : "invariable"},
           { "id" : "t1.4", "begin" : "6", "end" : "7", "form" : "\"", "lemma" : "\"", "tag" : "Fe", "ctag" : "Fe", "pos" : "punctuation", "type" : "quotation"},
           { "id" : "t1.5", "begin" : "7", "end" : "8", "form" : ":", "lemma" : ":", "tag" : "Fd", "ctag" : "Fd", "pos" : "punctuation", "type" : "colon"},
           { "id" : "t1.6", "begin" : "8", "end" : "9", "form" : "\"", "lemma" : "\"", "tag" : "Fe", "ctag" : "Fe", "pos" : "punctuation", "type" : "quotation"},
           { "id" : "t1.7", "begin" : "9", "end" : "10", "form" : "i", "lemma" : "il", "tag" : "DA0MP0", "ctag" : "DA", "pos" : "determiner", "type" : "article", "gen" : "masculine", "num" : "plural"},
           { "id" : "t1.8", "begin" : "11", "end" : "15", "form" : "topi", "lemma" : "topo", "tag" : "NCMP000", "ctag" : "NC", "pos" : "noun", "type" : "common", "gen" : "masculine", "num" : "plural"},
           { "id" : "t1.9", "begin" : "16", "end" : "19", "form" : "non", "lemma" : "non", "tag" : "RG", "ctag" : "RG", "pos" : "adverb", "type" : "general"},
           { "id" : "t1.10", "begin" : "20", "end" : "27", "form" : "avevano", "lemma" : "avere", "tag" : "VAII3P0", "ctag" : "VAI", "pos" : "verb", "type" : "auxiliary", "mood" : "indicative", "tense" : "imperfect", "person" : "3", "num" : "plural"},
           { "id" : "t1.11", "begin" : "28", "end" : "34", "form" : "nipoti", "lemma" : "nipote", "tag" : "NCCP000", "ctag" : "NC", "pos" : "noun", "type" : "common", "gen" : "common", "num" : "plural"},
           { "id" : "t1.12", "begin" : "34", "end" : "35", "form" : "\"", "lemma" : "\"", "tag" : "Fe", "ctag" : "Fe", "pos" : "punctuation", "type" : "quotation"},
           { "id" : "t1.13", "begin" : "35", "end" : "36", "form" : "}", "lemma" : "}", "tag" : "Flt", "ctag" : "Flt", "pos" : "punctuation", "type" : "curlybracket", "punctenclose" : "close"},
           { "id" : "t1.14", "begin" : "37", "end" : "38", "form" : ".", "lemma" : ".", "tag" : "Fp", "ctag" : "Fp", "pos" : "punctuation", "type" : "period"}]}]}"


config: {adapter: ƒ, transformRequest: {…}, transformResponse: {…}, timeout: 0, xsrfCookieName: "XSRF-TOKEN", …}
data: {id: "5c9d4a3f388c5b0e2088c10b", solutionText: "   { "sentences" : [↵      { "id":"1",↵        "to…, "pos" : "punctuation", "type" : "period"}]}]}↵", dateSolution: "2019-03-28T22:27:11.660+0000", affidability: 0, authorId: null, …}
headers: {pragma: "no-cache", content-type: "application/json;charset=UTF-8", cache-control: "no-cache, no-store, max-age=0, must-revalidate", expires: "0"}
request: XMLHttpRequest {onreadystatechange: ƒ, readyState: 4, timeout: 0, withCredentials: false, upload: XMLHttpRequestUpload, …}
status: 200
statusText: ""
__proto__: Object

*/
