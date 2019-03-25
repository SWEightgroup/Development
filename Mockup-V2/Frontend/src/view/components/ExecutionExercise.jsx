import React, { Component } from 'react';
import Word from '../containers/Word';
import { gerarchia } from '../../assets/lib/gerarchia';
import { _translator } from './Translator';

class ExecutionExercise extends Component {
  /**
   * call the confirm parent method
   */
  confirm = () => {
    const { checkExerciseFunction } = this.props;
    checkExerciseFunction();
  };

  salva = () => {
    const { salvaEsercizio } = this.props;
    salvaEsercizio();
  };

  render() {
    const {
      sentence,
      response,
      showSolution,
      lockInput,
      createAt
    } = this.props;

    if (sentence && sentence.length) {
      return (
        <div className="row">
          <div className="col-12">
            <div className="main-card mb-3 card">
              <div className="card-body">
                <h5 className="card-title">
                  {_translator('executionExercise_completeExercise')}
                </h5>
                <ul className="list-group">
                  {sentence &&
                    sentence.map((item, index) => {
                      return (
                        <Word
                          key={`${index + item + createAt}word`}
                          parola={item}
                          gerarchy={gerarchia}
                          index={index}
                          solutionAuto={
                            response && response.length ? response[index] : []
                          }
                          showSolution={showSolution}
                          lock={lockInput}
                        />
                      );
                    })}
                </ul>

                <div className="row justify-content-end px-3">
                  <div className="col-12 col-sm-4 py-2 px-0">
                    <button
                      type="button"
                      className="btn btn-success btn-block"
                      onClick={this.confirm}
                    >
                      {_translator('executionExercise_complete')}
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      );
    }
    return <React.Fragment />;
  }
}

export default ExecutionExercise;
