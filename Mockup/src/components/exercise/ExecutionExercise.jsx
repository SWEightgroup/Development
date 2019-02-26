import React, { Component } from 'react';
import Word from './Word';

class ExecutionExercise extends Component {
  state = {
    it: {
      adjective: {
        text: 'adjective',
        data: {
          type: [['ordinal', 'O'], ['qualificative', 'Q'], ['possessive', 'P']],
          degree: [['superlative', 'S']],
          gen: [
            ['feminile', 'F'],
            ['masculine', 'M'],
            ['common', 'C'],
            ['neuter', 'N']
          ],
          num: [['singular', 'S'], ['plural', 'P'], ['invariable', 'N']],
          possessorpers: [['1', '1'], ['2', '2'], ['3', '3']],
          possessornum: [
            ['singular', 'S'],
            ['plural', 'P'],
            ['invariable', 'N']
          ]
        }
      },
      conjunction: {
        text: 'conjunction',
        data: {
          type: [['coordinating', 'C'], ['subordinating', 'S']]
        }
      },
      determiner: {
        text: 'determiner',
        data: {
          type: [
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
  };

  render() {
    const { phrase } = this.props;
    const { it } = this.state;

    let out = null;
    if (phrase && phrase.length) {
      out = (
        <div className="row">
          <div className="col-12">
            <div className="main-card mb-3 card">
              <div className="card-body">
                <h5 className="card-title">Esegui l&rsquoesercizio</h5>
                <ul className="list-group">
                  {phrase &&
                    phrase.map(item => {
                      return <Word key={`s${item}`} parola={item} ger={it} />;
                    })}
                </ul>

                <div className="row justify-content-end px-3">
                  <div className="col-12 col-sm-4 py-2 px-0">
                    <button type="button" className="btn btn-success btn-block">
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
