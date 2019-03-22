import React, { Component } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import InputSentence from '../components/InputSentence';
import ExecutionExercise from '../components/ExecutionExercise';
import {
  initializeNewExercise,
  updateNewExerciseState,
  prepareExercise
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
  prepareExercise = () => {
    this.props.prepareExercise();
    const now = Date.now();
    const { exercise } = this.props;
    updateNewExerciseState({
      ...exercise,
      showSolution: false,
      response: null,
      createAt: now
    });

    const sentenceArray = exercise.sentenceString.split(' ');
    /*if (sentenceArray.length > 0) {
      updateNewExerciseState({
        ...exercise,
        sentence: sentenceArray
      });*/
    this.getSolution();
    //}
  };

  /**
   * set the showSolution flag to true
   */
  checkSolution = () => {
    updateNewExerciseState({
      ...this.props.exercise,
      showSolution: true
    });
  };

  salvaEsercizio = () => {
    axios
      .post(`http://localhost:8081/sw/salvaEsercizio`, {
        token: this.props.token,
        text: this.state.sentenceString,
        soluzione: this.state.response
      })
      .then(res => {
        console.log('salvataggio avvenuto');
      })
      .catch(() => console.log('ERRORE DI SALVATAGGIO'));
  };
  /**
   * call the server to analyze the sentence
   */
  getSolution = () => {
    const { sentenceString } = this.props.newExercise;

    console.log('CERCO LA SOLUZIONE', sentenceString);
    // qui faremo la chiamata

    // la soluzione sarà formata da un array di parola/codice
    axios
      .post(`http://localhost:8081/sw/s`, {
        text: sentenceString.trim()
      })
      .then(res => {
        console.log('SOLUZIONE TROVATA', res);
        updateNewExerciseState({
          ...this.props.exercise,
          response: JSON.parse(res.data.entity).sentences[0].tokens
        });
      })
      .catch(e => console.log(e));
  };

  render() {
    const {
      sentence,
      response,
      showSolution,
      createAt
    } = this.props.newExercise;

    return (
      <div className="col-12 col-md-10">
        <InputSentence prepareExercise={this.prepareExercise} />
        <ExecutionExercise
          sentence={sentence}
          checkExerciseFunction={this.checkSolution}
          response={response}
          showSolution={showSolution}
          createAt={createAt}
          salvaEsercizio={this.salvaEsercizio}
        />
      </div>
    );
  }
}
const mapStateToProps = store => {
  console.log(store);
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
    prepareExercise: () => dispatch(prepareExercise())
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NewExsercise);
