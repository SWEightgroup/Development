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
      .post(`http://localhost:8081/sw/s`, {
        text: sentenceString.trim()
      })
      .then(res => {
        updateNewExerciseState({
          ...this.props.newExercise,
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
      createAt,
      sentenceString
    } = this.props.newExercise;
    return (
      <div className="app-main__inner full-height-mobile">
        <div className="col-12 col-md-10">
          <InputSentence
            prepareExercise={this.prepareExercise}
            changeNewInputSentence={this.props.changeNewInputSentence}
            sentenceString={sentenceString}
          />
          <ExecutionExercise
            sentence={sentence}
            checkExerciseFunction={this.checkSolution}
            response={response}
            showSolution={showSolution}
            createAt={createAt}
            salvaEsercizio={this.salvaEsercizio}
          />
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
