import React, { Component } from 'react';
import { connect } from 'react-redux';
import ExecutionExercise from '../../components/ExecutionExercise';
import _translator from '../../../helpers/Translator';
import { removePunctuation } from '../../../helpers/Utils';

import {
  initializeNewExercise,
  updateNewExerciseState,
  saveSolution,
  initNewExerciseState
} from '../../../actions/ExerciseActions';

class HomeworkExercise extends Component {
  constructor(props) {
    super(props);
    if (props.newExercise.sentenceString === '') props.history.push('homework');
  }

  componentWillUnmount() {
    const { initializeNewExerciseDispatch } = this.props;
    initializeNewExerciseDispatch();
  }

  /**
   * set the showSolution flag to true
   */

  checkSolution = () => {
    const {
      newExercise,
      saveSolutionDispatch,
      updateNewExerciseStateDispatch
    } = this.props;

    const codeSolution = newExercise.userSolution[0].map((word, index) => {
      if (
        newExercise.response !== null &&
        word.languageIterator.getSolution() &&
        word.languageIterator.getSolution().length === 0 &&
        newExercise.response[index].tag.charAt(0) === 'F'
      ) {
        return newExercise.response[index].tag;
      }
      return word.languageIterator.getCodeSolution();
    }); // questo è un array di codici che invio al backend

    updateNewExerciseStateDispatch({
      ...newExercise,
      showSolution: true
    });
    saveSolutionDispatch({
      ...newExercise,
      codeSolution: [codeSolution, []]
    });
  };

  render() {
    const { newExercise, auth } = this.props;
    const { user } = auth;

    const { response, showSolution, createAt, mark } = newExercise;

    const { language } = user;

    const sentenceString = removePunctuation(newExercise.sentenceString);
    const sentence =
      sentenceString !== ''
        ? sentenceString.split(' ').filter(item => item.charAt(0) !== 'F')
        : [];
    let markClass = 'alert-danger';
    if (mark && mark > 8) markClass = 'alert-success';
    if (mark && mark <= 8 && mark > 5) markClass = 'alert-warning';
    return (
      <div className="row justify-content-center">
        <div className="col-12 col-md-10">
          <div className="main-card mb-3 card">
            <div className="card-body">
              <div className="row">
                <div className="col">
                  <h4 className="card-title">
                    <small>{_translator('gen_phrase', language)}: </small>
                    {newExercise.sentenceString}
                  </h4>
                </div>
                {mark !== undefined && mark !== null && (
                  <div className="col-md-auto">
                    <div
                      className={`alert pull-right ${markClass}`}
                      role="alert"
                    >
                      {mark}/10
                    </div>
                  </div>
                )}
              </div>
            </div>
          </div>
          <ExecutionExercise
            sentence={sentence} // array di parole
            response={response}
            showSolution={showSolution}
            createAt={createAt}
            salvaEsercizio={this.salvaEsercizio}
            language={language}
            showButton
            indexSolution={0}
          />

          {sentence && sentence.length > 0 && (
            <div className="main-card mb-3 card no-bg-color">
              <div className="card-body">
                <div className="row justify-content-end ">
                  <div className="col-12 col-sm-4 py-0 px-0">
                    <button
                      type="button"
                      className="btn btn-success btn-block"
                      onClick={this.checkSolution}
                      disabled={showSolution}
                    >
                      {_translator('executionExercise_complete', language)}
                    </button>
                  </div>
                </div>
              </div>
            </div>
          )}
        </div>
      </div>
    );
  }
}
const mapStateToProps = store => {
  return {
    authError: store.auth.authError,
    auth: store.auth,
    token: store.auth.user.token,
    newExercise: store.exercise.newExercise
  };
};

const mapDispatchToProps = dispatch => {
  return {
    initializeNewExerciseDispatch: () => dispatch(initializeNewExercise()),
    updateNewExerciseStateDispatch: newExercise =>
      dispatch(updateNewExerciseState(newExercise)),
    saveSolutionDispatch: newExercise => dispatch(saveSolution(newExercise)),
    initNewExerciseStateDispatch: newExercise =>
      dispatch(initNewExerciseState(newExercise))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HomeworkExercise);

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
