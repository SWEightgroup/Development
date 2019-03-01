import React, { Component } from 'react';
import Word from './Word';

class ExecutionExercise extends Component {
  state = {
    it: {
      adjective: {
        text: ['adjective', 'A'],
        data: {
          type: {
            text: 'type',
            data: [
              ['ordinal', 'O'],
              ['qualificative', 'Q'],
              ['possessive', 'P']
            ]
          },
          degree: { text: 'degree', data: [['superlative', 'S']] },
          gen: {
            text: 'gen',
            data: [
              ['feminile', 'F'],
              ['masculine', 'M'],
              ['common', 'C'],
              ['neuter', 'N']
            ]
          },
          num: {
            text: 'num',
            data: [['singular', 'S'], ['plural', 'P'], ['invariable', 'N']]
          },
          possessorpers: {
            text: 'possessorpers',
            data: [['1', '1'], ['2', '2'], ['3', '3']]
          },
          possessornum: {
            text: 'possessornum',
            data: [['singular', 'S'], ['plural', 'P'], ['invariable', 'N']]
          }
        }
      },
      conjunction: {
        text: ['conjunction', 'C'],
        data: {
          type: {
            text: 'type',
            data: [['coordinating', 'C'], ['subordinating', 'S']]
          }
        }
      },
      determiner: {
        text: ['determiner', 'A'],
        data: {
          type: {
            text: 'type',
            data: [
              ['article', 'A'],
              ['demonstrative', 'D'],
              ['exclamative', 'E'],
              ['indefinite', 'I'],
              ['interrogative', 'T'],
              ['numeral', 'N'],
              ['possessive', 'P']
            ]
          }
        }
      }
    }
  };

  confirm = () => {
    const { checkExerciseFunction } = this.props;
    checkExerciseFunction();
  };

  render() {
    const { phrase, response, showSolution } = this.props;
    const { it } = this.state;

    let out = null;
    if (phrase && phrase.length) {
      out = (
        <div className="row">
          <div className="col-12">
            <div className="main-card mb-3 card">
              <div className="card-body">
                <h5 className="card-title">Esegui l'esercizio</h5>
                <ul className="list-group">
                  {phrase &&
                    phrase.map((item, index) => {
                      return (
                        <Word
                          key={`s${index}`}
                          parola={item}
                          gerarchy={it}
                          index={index}
                          solutionAuto={
                            response && response.length ? response[index] : []
                          }
                          showSolution={showSolution}
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
                      Completa
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      );
    }

    return <React.Fragment>{out}</React.Fragment>;
  }
}

export default ExecutionExercise;
