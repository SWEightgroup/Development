import React, { Component } from 'react';
import { connect } from 'react-redux';

import _translator from '../../helpers/Translator';
import LanguageStructure from '../../helpers/LanguageIterator';
import SolutionMapper from '../../helpers/SolutionMapper';
import { updateWordState } from '../../actions/ExerciseActions';

class Word extends Component {
  constructor(props) {
    super(props);
    const {
      gerarchy,
      updateWordStateDispatch,
      solutionTag,
      indexSolution,
      language
    } = props;
    const languageIterator = new LanguageStructure(gerarchy).getBaseIterator();

    updateWordStateDispatch(
      {
        languageIterator,
        buttons: languageIterator.getCurrentButtonList(),
        solution:
          solutionTag && props.initSolution && solutionTag.charAt(0) !== 'F'
            ? new SolutionMapper(solutionTag, gerarchy).getVerboseSolution(
                language
              )
            : '',
        index: props.index,
        showButton: props.showButton
      },
      indexSolution
    );
  }

  /**
   *
   * Generates the first set of buttons given by the user choice
   *
   * @param {*} item Selected item (level 0)
   */

  backOne = () => {
    try {
      const {
        updateWordStateDispatch,
        newExercise,
        index,
        indexSolution,
        language
      } = this.props;
      const state = newExercise.userSolution[indexSolution][index];
      const { languageIterator } = state;
      languageIterator.prevLevel();

      updateWordStateDispatch(
        {
          ...state,
          languageIterator,
          buttons: languageIterator.getCurrentButtonList(),
          solution: languageIterator.getVerboseSolution(language)
        },
        indexSolution
      );
    } catch (err) {
      console.error(err);
    }
  };

  /**
   * Genrates next list of buttons
   *
   * @param {*} item  Selected elementend (level 1+)
   */
  generateNext = button => {
    try {
      const {
        updateWordStateDispatch,
        newExercise,
        index,
        indexSolution,
        language
      } = this.props;
      const state = newExercise.userSolution[indexSolution][index];
      const { languageIterator } = state;
      languageIterator.nextLevel(button);
      updateWordStateDispatch(
        {
          ...state,
          buttons: languageIterator.getCurrentButtonList(),
          solution: languageIterator.getVerboseSolution(language),
          languageIterator
        },
        indexSolution
      );
    } catch (err) {
      console.error(err);
    }
  };

  /**
   * Reset the word solution and buttons
   */
  reset = () => {
    const {
      updateWordStateDispatch,
      newExercise,
      index,
      indexSolution,
      language
    } = this.props;
    const state = newExercise.userSolution[indexSolution][index];
    const { languageIterator } = state;
    languageIterator.setBaseLevel();
    updateWordStateDispatch(
      {
        ...state,
        buttons: languageIterator.getCurrentButtonList(),
        solution: languageIterator.getVerboseSolution(language),
        languageIterator
      },
      indexSolution
    );
  };

  edit = () => {
    const {
      updateWordStateDispatch,
      newExercise,
      index,
      indexSolution
    } = this.props;

    const state = newExercise.userSolution[indexSolution][index];
    updateWordStateDispatch(
      {
        ...state,
        showButton: true
      },
      indexSolution
    );
  };

  render() {
    const {
      parola,
      solutionTag,
      showSolution,
      gerarchy,
      newExercise,
      index,
      initSolution,
      indexSolution,
      language
    } = this.props;

    const state = newExercise.userSolution[indexSolution][index];
    const { buttons, solution, showButton } = state;
    const allowedPunctuation = /[,.?!"'<>;:]/g;

    if (parola.match(allowedPunctuation) !== null) {
      return <React.Fragment />;
    }
    return (
      <li
        className="list-group-item"
        ref={liElement => {
          this.liElement = liElement;
        }}
      >
        <div>
          <div className="container-fluid">
            <div className="row ">
              <div className="pt-2  col-md-auto">
                {buttons && showButton && !showSolution && (
                  <React.Fragment>
                    <button
                      type="button"
                      className="border-0 btn btn-outline-danger btn-sm"
                      onClick={this.backOne}
                      disabled={solution.length === 0}
                    >
                      <i className="fa fa-fw fa-undo" />
                    </button>
                    <button
                      type="button"
                      className="border-0 btn btn-outline-danger btn-sm"
                      onClick={this.reset}
                      disabled={solution.length === 0}
                    >
                      <i className="fa fa-fw fa-trash" />
                    </button>
                  </React.Fragment>
                )}
                {buttons && !showButton && !showSolution && initSolution && (
                  <button
                    type="button"
                    className="border-0 btn btn-outline-danger btn-sm"
                    onClick={this.edit}
                  >
                    MODIFICA
                  </button>
                )}

                <strong className="p-2 text-uppercase">{parola}</strong>
              </div>

              {/** USER SOLUTION */}
              <div className=" col-md-auto text-uppercase shwo-tooltip">
                {solution && (
                  <p title="La tua soluzione" className="bg-light p-2 mb-2">
                    {solution}
                  </p>
                )}

                {/** SYSTEM/TEACHER SOLUTION */}
                {showSolution && solutionTag && (
                  <p
                    title="La soluzione automatica"
                    className=" text-warning my-2 text-uppercase"
                  >
                    {new SolutionMapper(
                      solutionTag,
                      gerarchy
                    ).getVerboseSolution(language)}
                  </p>
                )}
              </div>
            </div>
            {/* <div className="row justify-content-end">
              <div className=" col-md-9 col-12 text-warning text-uppercase">
                {showSolution === true && usefulFields}
              </div>
            </div> */}
          </div>
        </div>

        {buttons &&
          showSolution === false &&
          showButton &&
          buttons.map((button, index) => {
            return (
              <button
                type="button"
                className="btn btn-outline-primary m-1"
                key={`index${index}`}
                onClick={() => this.generateNext(button)}
              >
                {_translator(button.full, language)}
              </button>
            );
          })}
      </li>
    );
  }
}
const mapStateToProps = store => {
  return {
    language: store.auth.user.language,
    newExercise: store.exercise.newExercise
  };
};

const mapDispatchToProps = dispatch => {
  return {
    updateWordStateDispatch: (word, indexSolution) =>
      dispatch(updateWordState(word, indexSolution))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Word);
