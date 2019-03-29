import React, { Component } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import InputSentence from '../components/InputSentence';
import ExecutionExercise from '../components/ExecutionExercise';
import {
  initializeNewExercise,
  updateNewExerciseState,
  changeNewInputSentence
} from '../../actions/ExerciseActions';

class NewExsercise extends Component {
  constructor(props) {
    super(props);
    props.initializeNewExercise();
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
    updateNewExerciseState({
      ...newExercise,
      showSolution: false,
      response: null,
      createAt: now,
      sentence: sentenceArray
    });

    this.getSolution();
  };

  changeNewInputSentence = input => {
    this.props.changeNewInputSentence({
      ...this.props.newExercise,
      sentenceString: input
    });
  };

  /**
   * set the showSolution flag to true
   */
  checkSolution = () => {
    updateNewExerciseState({
      ...this.props.newExercise,
      showSolution: true
    });
  };

  /**
   * call the server to analyze the sentence
   */
  getSolution = () => {
    const { sentenceString } = this.props.newExercise;
    // qui faremo la chiamata
    // la soluzione sarà formata da un array di parola/codice
    axios
      .post(
        `http://localhost:8081/exercises/automatic-solution`,
        {
          text: sentenceString.trim()
        },
        {
          headers: {
            Authorization: this.props.auth.token
          }
        }
      )
      .then(res => {
        console.log(
          ': NewExsercise -> getSolution -> res',
          JSON.parse(res.data.solutionText).sentences[0].tokens.filter(
            token => token.tag.charAt(0) !== 'F'
          )
        );

        updateNewExerciseState({
          ...this.props.newExercise,
          response: JSON.parse(
            res.data.solutionText
          ).sentences[0].tokens.filter(token => token.tag.charAt(0) !== 'F')
        });
      })
      .catch(e => console.log(e));
  };

  render() {
    const {
      sentence,
      response,
      showSolution,
      createAt,
      sentenceString
    } = this.props.newExercise;

    return (
      <div className="app-main__inner full-height-mobile">
        <div className="row justify-content-center">
          <div className="col-12 col-md-10">
            <InputSentence
              prepareExercise={this.prepareExercise}
              changeNewInputSentence={this.props.changeNewInputSentence}
              sentenceString={sentenceString}
            />
            <ExecutionExercise
              sentence={sentence} // array di parole
              checkExerciseFunction={this.checkSolution}
              response={response}
              showSolution={showSolution}
              createAt={createAt}
              salvaEsercizio={this.salvaEsercizio}
            />
          </div>
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
    initializeNewExercise: () => dispatch(initializeNewExercise()),
    updateNewExerciseState: newExercise =>
      dispatch(updateNewExerciseState(newExercise)),
    changeNewInputSentence: input => dispatch(changeNewInputSentence(input))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NewExsercise);

/* 

"   { "sentences" : [
      { "id":"1",
        "tokens" : [
           { "id" : "t1.1", "begin" : "0", "end" : "10", "form" : "sebastiano", "lemma" : "sebastiano", "tag" : "AQ0MS00", "ctag" : "AQ", "pos" : "adjective", "type" : "qualificative", "gen" : "masculine", "num" : "singular"},
           { "id" : "t1.2", "begin" : "11", "end" : "18", "form" : "caccaro", "lemma" : "caccaro", "tag" : "AQ0MS00", "ctag" : "AQ", "pos" : "adjective", "type" : "qualificative", "gen" : "masculine", "num" : "singular"},
           { "id" : "t1.3", "begin" : "19", "end" : "27", "form" : "facebook", "lemma" : "facebook", "tag" : "NCMN000", "ctag" : "NC", "pos" : "noun", "type" : "common", "gen" : "masculine", "num" : "invariable"},
           { "id" : "t1.4", "begin" : "28", "end" : "29", "form" : ".", "lemma" : ".", "tag" : "Fp", "ctag" : "Fp", "pos" : "punctuation", "type" : "period"}]}]}
" 




config: {adapter: ƒ, transformRequest: {…}, transformResponse: {…}, timeout: 0, xsrfCookieName: "XSRF-TOKEN", …}
data: {id: "5c9d4a3f388c5b0e2088c10b", solutionText: "   { "sentences" : [↵      { "id":"1",↵        "to…, "pos" : "punctuation", "type" : "period"}]}]}↵", dateSolution: "2019-03-28T22:27:11.660+0000", affidability: 0, authorId: null, …}
headers: {pragma: "no-cache", content-type: "application/json;charset=UTF-8", cache-control: "no-cache, no-store, max-age=0, must-revalidate", expires: "0"}
request: XMLHttpRequest {onreadystatechange: ƒ, readyState: 4, timeout: 0, withCredentials: false, upload: XMLHttpRequestUpload, …}
status: 200
statusText: ""
__proto__: Object

*/
