import React, { Component } from 'react';
import { connect } from 'react-redux';
import InputSentence from '../../components/InputSentence';
import ExecutionExercise from '../../components/ExecutionExercise';
import _translator from '../../../helpers/Translator';

import {
  initializeNewExercise,
  updateNewExerciseState,
  changeNewInputSentence,
  saveFreeExercise,
  initNewExerciseState,
  getAutomaticSolution
} from '../../../actions/ExerciseActions';

class NewExsercise extends Component {
  constructor(props) {
    super(props);
    props.initializeNewExercise();
  }

  componentWillUnmount() {
    this.props.initializeNewExercise();
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
      showButton: true,
      response: null,
      createAt: now,
      sentence: sentenceArray
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
      saveFreeExerciseDispatch,
      updateNewExerciseStateDispatch
    } = this.props;

    const codeSolution = newExercise.userSolution[0].map((word, index) => {
      if (
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
    saveFreeExerciseDispatch({
      ...newExercise,
      codeSolution: [codeSolution, []]
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
    initNewExerciseStateDispatch({
      ...newExercise,
      response: null
    });
    const { sentenceString } = newExercise;
    const toSend = sentenceString.replace('.', ' ');
    getAutomaticSolutionDispatch(toSend);
  };

  render() {
    const { changeNewInputSentenceDispatch, newExercise, auth } = this.props;
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
      <div className="container ml-0">
        <div className="row justify-content-center">
          <div className="col-12 col-md-10">
            <InputSentence
              prepareExercise={this.prepareExercise}
              changeNewInputSentence={changeNewInputSentenceDispatch}
              sentenceString={sentenceString}
              language={language}
            />
            <ExecutionExercise
              sentence={sentence} // array di parole
              response={response}
              showSolution={showSolution}
              createAt={createAt}
              showButton={showButton}
              language={language}
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
    updateNewExerciseStateDispatch: newExercise =>
      dispatch(updateNewExerciseState(newExercise)),
    changeNewInputSentenceDispatch: input =>
      dispatch(changeNewInputSentence(input)),
    saveFreeExerciseDispatch: newExercise =>
      dispatch(saveFreeExercise(newExercise)),
    initNewExerciseStateDispatch: newExercise =>
      dispatch(initNewExerciseState(newExercise)),
    getAutomaticSolutionDispatch: sentenceString =>
      dispatch(getAutomaticSolution(sentenceString))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NewExsercise);
