import React, { Component } from 'react';
import { connect } from 'react-redux';
import Word from '../containers/Word';
import { gerarchia } from '../../assets/lib/gerarchia';

class ExecutionExercise extends Component {
  state = {
    gerarchy: gerarchia
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
    const { gerarchy } = this.state;
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
                          gerarchy={gerarchy}
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
