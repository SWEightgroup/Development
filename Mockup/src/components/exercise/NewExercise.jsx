import React, { Component } from 'react';
import axios from 'axios';
import InputPhrase from './InputPhrase';
import ExecutionExercise from './ExecutionExercise';

class NewExsercise extends Component {
  state = {
    phrase: [],
    phraseString: '',
    response: null,
    showSolution: false
  };

  prepareExercise = phrase => {
    this.setState({ phraseString: phrase });
    const phraseArray = phrase.split(' ');
    if (phraseArray.length > 0) {
      this.setState({ phrase: phraseArray });
    }
    this.getSolution(phrase);
  };

  checkSolution = () => {
    this.setState({ showSolution: true });
  };

  getSolution = phrase => {
    // qui faremo la chiamata
    // la soluzione sarà formata da un array di parola/codice
    axios
      .post(`http://localhost:8081/grammatical-analysis/p`, {
        phrase
      })
      .then(res => {
        this.setState({ response: res.data.sentences[0].tokens });
      })
      .catch(err => console.log(err));
  };

  render() {
    const { phrase, response, showSolution } = this.state;
    return (
      <div className="col-12 col-md-10">
        <InputPhrase prepareExercise={this.prepareExercise} />
        <ExecutionExercise
          phrase={phrase}
          checkExerciseFunction={this.checkSolution}
          response={response}
          showSolution={showSolution}
        />
      </div>
    );
  }
}

export default NewExsercise;
