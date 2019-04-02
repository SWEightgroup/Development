import React, { Component } from 'react';
import Word from './Word';
import gerarchia from '../../constants/gerarchia';
import _translator from '../../helpers/Translator';
import SolutionMapper, {
  solutionTranslator
} from '../../helpers/SolutionTranslator';

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

  extractTag = (response, index) => {
    return response[index].tag;
  };

  render() {
    const allowedPunctuation = /[,.?!"'<-\\{}>;:]/g;
    // const allowedPunctuation2 = /[,.?!"'<-{}[]()%\/>;:]/g;
    const {
      sentence,
      response,
      showSolution,
      lockInput,
      createAt,
      language
    } = this.props;
    const pippo = sentence.filter(word => !word.match(allowedPunctuation));
    if (sentence && sentence.length) {
      return (
        <div className="row">
          <div className="col-12">
            <div className="main-card mb-3 card">
              <div className="card-body">
                <h5 className="card-title">
                  {_translator('executionExercise_completeExercise', language)}
                </h5>
                <ul className="list-group">
                  {pippo &&
                    pippo.map((item, index) => {
                      return (
                        <Word
                          key={`${index + item + createAt}word`}
                          parola={item}
                          gerarchy={gerarchia}
                          index={index}
                          solutionTag={
                            response && response.length
                              ? this.extractTag(response, index)
                              : null
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
                      {_translator('executionExercise_complete', language)}
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
