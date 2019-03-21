import React, { Component } from 'react';
import { connect } from 'react-redux';
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
          degree: {
            text: 'degree',
            data: [['superlative', 'S'], ['none', '0']]
          },
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
          },
          person: {
            text: 'person',
            data: [['1', '1'], ['2', '2'], ['3', '3']]
          },
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
          possessornum: {
            text: 'possessornum',
            data: [['singular', 'S'], ['plural', 'P']]
          }
        }
      },
      verb: {
        text: ['verb', 'V'],
        data: {
          type: {
            text: 'type',
            data: [['main', 'M'], ['auxiliary', 'A'], ['semiauxiliary', 'S']]
          },
          mood: {
            text: 'mood',
            data: [
              ['indicative', 'I'],
              ['subjunctive', 'S'],
              ['imperative', 'M'],
              ['pastparticiple', 'P'],
              ['gerund', 'G'],
              ['infinitive', 'N']
            ]
          },
          tense: {
            text: 'tense',
            data: [
              ['present', 'P'],
              ['imperfect', 'I'],
              ['future', 'F'],
              ['past', 'S'],
              ['conditional', 'C']
            ]
          },
          person: {
            text: 'person',
            data: [['1', '1'], ['2', '2'], ['3', '3']]
          },
          num: {
            text: 'num',
            data: [['singular', 'S'], ['plural', 'P']]
          },
          gen: {
            text: 'gen',
            data: [
              ['feminile', 'F'],
              ['masculine', 'M'],
              ['common', 'C'],
              ['neuter', 'N']
            ]
          }
        }
      },
      noun: {
        text: ['noun', 'N'],
        data: {
          type: {
            text: 'type',
            data: [['common', 'C'], ['common', 'P']]
          },
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
            data: [['singular', 'S'], ['plural', 'P']]
          },
          neclass: {
            text: 'neclass',
            data: [
              ['person', 'S'],
              ['location', 'G'],
              ['oranization', 'O'],
              ['other', 'V']
            ]
          },
          person: {
            text: 'person',
            data: [['augmentative', 'A'], ['diminutive', 'D']]
          }
        }
      }
    }
  };

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
    const { it } = this.state;
    let out = null;

    if (sentence && sentence.length) {
      out = (
        <div className="row">
          <div className="col-12">
            <div className="main-card mb-3 card">
              <div className="card-body">
                <h5 className="card-title">Esegui l'esercizio</h5>
                <ul className="list-group">
                  {sentence &&
                    sentence.map((item, index) => {
                      return (
                        <Word
                          key={`${index + item + createAt}word`}
                          parola={item}
                          gerarchy={it}
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
                      Completa
                    </button>
                    {showSolution && (
                      <button type="button" onClick={this.props.salvaEsercizio}>
                        Salva
                      </button>
                    )}
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

const mapStateToProps = store => {
  return {
    authError: store.auth.authError,
    auth: store.auth
  };
};

export default connect(mapStateToProps)(ExecutionExercise);
