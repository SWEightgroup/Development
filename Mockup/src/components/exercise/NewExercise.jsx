import React, { Component } from 'react';
import axios from 'axios';
import InputPhrase from './InputPhrase';
import ExecutionExercise from './ExecutionExercise';

class NewExsercise extends Component {
  state = {
    phrase: [],
    phraseString: ''
  };

  prepareExercise = phrase => {
    this.setState({ phraseString: phrase }, () => this.getSolution());
    const phraseArray = phrase.split(' ');
    if (phraseArray.length > 0) {
      this.setState({ phrase: phraseArray });
    }
  };

  getSolution = () => {
    const { phraseString } = this.state;
    // qui faremo la chiamata
    // la soluzione sarà formata da un array di parola/codice
    axios
      .post(`http://localhost:8081/grammatical-analysis/p`, {
        phrase: phraseString
      })
      .then(res => {
        console.log(res.data);
      })
      .catch(err => console.log(err));
  };

  render() {
    const { phrase } = this.state;
    return (
      <div className="col-12 col-md-8">
        <InputPhrase prepareExercise={this.prepareExercise} />
        <ExecutionExercise phrase={phrase} />
      </div>
    );
  }
}

export default NewExsercise;
