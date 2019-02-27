import React, { Component } from 'react';
import LanguageStructure from '../../lib/LanguageCatIterator';

class Word extends Component {
  constructor(props) {
    super(props);
    const { gerarchy } = props;
    const language_iterator = new LanguageStructure(gerarchy).getBaseIterator(); // NON VA BENE, VA CAMBIATO
    this.state = {
      language_iterator,
      buttons: language_iterator.getCurrentButtonList(),
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
    const { language_iterator } = this.state;
    language_iterator.prevLevel();
    this.setState({
      language_iterator,
      buttons: language_iterator.getCurrentButtonList(),
      solution: language_iterator.getSolution()
    });
  };

  /**
   * Genrates next list of buttons
   *
   * @param {*} item  Selected elementend (level 1+)
   */
  generateNext = button => {
    const { language_iterator } = this.state;
    language_iterator.nextLevel(button);
    this.setState({
      buttons: language_iterator.getCurrentButtonList(),
      solution: language_iterator.getSolution(),
      language_iterator
    });
  };

  reset = () => {
    const { language_iterator } = this.state;
    language_iterator.setBaseLevel();
    this.setState({
      buttons: language_iterator.getCurrentButtonList(),
      solution: language_iterator.getSolution(),
      language_iterator
    });
  };

  render() {
    const { parola } = this.props;
    const { buttons, solution } = this.state;
    return (
      <li className="list-group-item">
        <p>
          <span>
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
            </button>{' '}
            {parola}
          </span>
          {' : '}
          <span>{solution}</span>
        </p>

        {buttons &&
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
