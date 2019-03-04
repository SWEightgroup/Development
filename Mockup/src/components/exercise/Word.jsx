import React, { Component } from 'react';
import LanguageStructure from '../../lib/LanguageCatIterator';

class Word extends Component {
  constructor(props) {
    super(props);
    const { gerarchy } = props;
    const languageIterator = new LanguageStructure(gerarchy).getBaseIterator(); // NON VA BENE, VA CAMBIATO

    this.state = {
      languageIterator,
      buttons: languageIterator.getCurrentButtonList(),
      solution: ''
    };
  }

  /**
   *
   * Generates the first set of buttons given by the user choice
   *
   * @param {*} item Selected item (level 0)
   */

  backOne = () => {
    try {
      const { languageIterator } = this.state;
      languageIterator.prevLevel();
      this.setState({
        languageIterator,
        buttons: languageIterator.getCurrentButtonList(),
        solution: languageIterator.getSolution()
      });
    } catch (err) {
      console.log(err);
    }
  };

  /**
   * Genrates next list of buttons
   *
   * @param {*} item  Selected elementend (level 1+)
   */
  generateNext = button => {
    try {
      const { languageIterator } = this.state;
      languageIterator.nextLevel(button);
      this.setState({
        buttons: languageIterator.getCurrentButtonList(),
        solution: languageIterator.getSolution(),
        languageIterator
      });
    } catch (err) {
      console.log(err);
    }
  };

  reset = () => {
    const { languageIterator } = this.state;
    languageIterator.setBaseLevel();
    this.setState({
      buttons: languageIterator.getCurrentButtonList(),
      solution: languageIterator.getSolution(),
      languageIterator
    });
  };

  render() {
    const { parola, solutionAuto, showSolution } = this.props;
    const { buttons, solution } = this.state;
    let usefulFields = '';
    if (solutionAuto) {
      const columns = Object.keys(solutionAuto).map(p =>
        Object.assign({ data: solutionAuto[p] }, { text: p })
      );
      usefulFields = columns
        .filter(
          item =>
            item.text !== 'id' &&
            item.text !== 'begin' &&
            item.text !== 'end' &&
            item.text !== 'form' &&
            item.text !== 'lemma' &&
            item.text !== 'tag' &&
            item.text !== 'ctag'
        )
        .map(item => item.data)
        .join(' ');
    }
    const allowedPunctuation = /[,.?!"'<>;:]/g;

    if (parola.match(allowedPunctuation) !== null) {
      return <React.Fragment />;
    }
    return (
      <li className="list-group-item">
        <div>
          <div className="container">
            <div className="row ">
              <div className=" col-md-3 col-12  ">
                {showSolution === false && (
                  <React.Fragment>
                    <button
                      type="button"
                      className="border-0 btn btn-outline-danger btn-sm"
                      onClick={this.backOne}
                    >
                      <i className="material-icons">settings_backup_restore</i>
                    </button>
                    <button
                      type="button"
                      className="border-0 btn btn-outline-danger btn-sm"
                      onClick={this.reset}
                    >
                      <i className="material-icons">delete_forever</i>
                    </button>
                  </React.Fragment>
                )}
                <span className="py-2 text-uppercase">{parola}</span>
              </div>

              <div className=" col-md-9 col-12 py-2 text-uppercase">
                {solution}
              </div>
            </div>
            <div className="row justify-content-end">
              <div className=" col-md-9 col-12 text-warning text-uppercase">
                {showSolution === true && usefulFields}
              </div>
            </div>
          </div>
        </div>

        {buttons &&
          showSolution === false &&
          buttons.map((button, index) => {
            return (
              <button
                type="button"
                className="btn btn-outline-primary m-1"
                key={`index${index}`}
                onClick={() => this.generateNext(button)}
              >
                {button}
              </button>
            );
          })}
      </li>
    );
  }
}
export default Word;
