import React, { Component } from 'react';

class Word extends Component {
  constructor(props) {
    super(props);
    this.state = {
      buttons: [],
      index: -1,
      levelZero: [],
      solution: ''
    };
  }

  /**
   * 
   * Generates the first set of buttons given by the user choice
   *
   * @param {*} item Selected item (level 0)
   */

  generateLevelOne = item => {
    const livello = Object.values(item.data)[0];
    if (livello)
      this.setState({
        index: 0,
        buttons: livello,
        levelZero: item,
        solution: item.text
      });
  };

  /**
   * Genrates next list of buttons
   *
   * @param {*} item  Selected elementend (level 1+)
   */
  generateNext = item => {
    const index = this.state + 1;
    const { levelZero, solution } = this.state;
    const buttons = Object.values(levelZero.data)[index];
    const nextSolution = `${solution} ${item[0]}`;
    if (buttons) {
      this.setState({ index, buttons, solution: nextSolution });
    } else this.setState({ buttons: [], solution: nextSolution });
  };

  render() {
    const { parola, ger } = this.props;
    const { buttons, solution, index } = this.state;
    const livello1 = Object.values(ger);
    return (
      <li className="list-group-item">
        <p>
          <span>
            <button
              type="button"
              className="border-0 btn btn-outline-danger btn-sm"
            >
              <i className="material-icons">settings_backup_restore</i>
            </button>{' '}
            {parola}
          </span>
          {' : '}
          <span>{solution}</span>
        </p>

        {/*Only for first level element(adjective, pronoun, ecc) */}
        {index === -1 &&
          livello1.map((item, index) => {
            return (
              <button
                type="button"
                className="btn btn-outline-primary m-1"
                key={`index${index}`}
                onClick={() => this.generateLevelOne(item, 0)}
              >
                {item.text}
              </button>
            );
          })}

        {/**
          Only for 1+ level elements
          */}
        {buttons.length > 0 &&
          buttons.map((item, index) => {
            return (
              <button
                type="button"
                className="btn btn-outline-primary m-1"
                key={`index${index}`}
                onClick={() => this.generateNext(item, index + 1)}
              >
                {item[0]}
              </button>
            );
          })}
      </li>
    );
  }
}
export default Word;
