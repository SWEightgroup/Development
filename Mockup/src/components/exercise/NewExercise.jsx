import React, { Component } from 'react';
import axios from 'axios';
import InputSentence from './InputSentence';
import ExecutionExercise from './ExecutionExercise';

class NewExsercise extends Component {
  state = {
    sentence: [],
    sentenceString: '',
    response: null,
    showSolution: false
  };

  prepareExercise = sentence => {
    this.setState({ sentenceString: sentence });
    const sentenceArray = sentence.split(' ');
    if (sentenceArray.length > 0) {
      this.setState({ sentence: sentenceArray }, () => {
        this.getSolution(sentence);
      });
    }
  };

  checkSolution = () => {
    this.setState({ showSolution: true });
  };

  getSolution = () => {
    const { sentenceString } = this.state;
    // qui faremo la chiamata
    // la soluzione sarà formata da un array di parola/codice
    axios
      .post(`http://sw8.tech/grammatical-analysis/p`, {
        phrase: sentenceString
      })
      .then(res => {
        console.log(res);
        this.setState({ response: res.data.sentences[0].tokens });
      })
      .catch(err => console.log(err));
  };

  render() {
    const { sentence, response, showSolution } = this.state;
    return (
      <div className="col-12 col-md-10">
        <InputSentence prepareExercise={this.prepareExercise} />
        <ExecutionExercise
          sentence={sentence}
          checkExerciseFunction={this.checkSolution}
          response={response}
          showSolution={showSolution}
        />
      </div>
    );
  }
}

export default NewExsercise;
