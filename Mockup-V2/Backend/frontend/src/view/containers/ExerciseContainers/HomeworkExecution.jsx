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
    const markToShow = !mark || Number.isNaN(mark) ? 0 : mark.toFixed(2);
    return (
      <div className="row justify-content-center student">
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
                      {markToShow}/10
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
