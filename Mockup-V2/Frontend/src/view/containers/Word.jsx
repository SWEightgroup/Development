import React, { Component } from 'react';
import LanguageStructure from '../../assets/lib/LanguageIterator';

class Word extends Component {
  constructor(props) {
    super(props);
    const { gerarchy } = props;
    const languageIterator = new LanguageStructure(gerarchy).getBaseIterator(); // NON VA BENE, VA CAMBIATO
    console.log('lista:', languageIterator.getCurrentButtonList());
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
        solution: languageIterator.getVerboseSolution(),
        languageIterator
      });
    } catch (err) {
      console.log(err);
    }
  };

  /**
   * Reset the word solution and buttons
   */
  reset = () => {
    const { languageIterator } = this.state;
    languageIterator.setBaseLevel();
    this.setState({
      buttons: languageIterator.getCurrentButtonList(),
      solution: languageIterator.getVerboseSolution(),
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
                {showSolution === false && (
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
                <strong className="p-2 text-uppercase">{parola}</strong>
              </div>

              <div className=" col-md-auto text-uppercase shwo-tooltip">
                {solution && (
                  <p title="La tua soluzione" className="bg-light p-2 mb-2">
                    {solution}
                  </p>
                )}

                {usefulFields && showSolution && (
                  <p
                    title="La soluzione automatica"
                    className=" text-warning my-2 text-uppercase"
                  >
                    {showSolution === true && usefulFields}
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
          buttons.map((button, index) => {
            return (
              <button
                type="button"
                className="btn btn-outline-primary m-1"
                key={`index${index}`}
                onClick={() => this.generateNext(button)}
              >
                {button.full}
              </button>
            );
          })}
      </li>
    );
  }
}
export default Word;
