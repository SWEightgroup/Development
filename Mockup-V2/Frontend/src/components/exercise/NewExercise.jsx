import React, { Component } from 'react';
import axios from 'axios';
import InputSentence from './InputSentence';
import ExecutionExercise from './ExecutionExercise';

class NewExsercise extends Component {
  constructor(props) {
    super(props);
    const now = Date.now();
    this.state = {
      sentence: [],
      sentenceString: '',
      response: null,
      showSolution: false,
      createAt: now
    };
  }

  /**
   * split the sentence
   *
   * @param sentence the sentence to analyze
   */
  prepareExercise = sentence => {
    const now = Date.now();
    this.setState({
      sentenceString: sentence,
      showSolution: false,
      response: null,
      createAt: now
    });
    const sentenceArray = sentence.split(' ');
    if (sentenceArray.length > 0) {
      this.setState({ sentence: sentenceArray }, () => {
        this.getSolution(sentence);
      });
    }
  };

  /**
   * set the showSolution flag to true
   */
  checkSolution = () => {
    this.setState({ showSolution: true });
  };

  /**
   * call the server to analyze the sentence
   */
  getSolution = () => {
    const { sentenceString } = this.state;
    // qui faremo la chiamata
    // la soluzione sarà formata da un array di parola/codice
    axios
      .post(`http://localhost:8081/sw/s`, {
        text: sentenceString.trim()
      })
      .then(res => {
        this.setState({
          response: JSON.parse(res.data.entity).sentences[0].tokens
        });
        // localStorage.setItem('user', JSON.stringify(res.data));
      })
      .catch(e => console.log(e));
  };

  render() {
    const { sentence, response, showSolution, createAt } = this.state;
    return (
      <div className="col-12 col-md-10">
        <InputSentence prepareExercise={this.prepareExercise} />
        <ExecutionExercise
          sentence={sentence}
          checkExerciseFunction={this.checkSolution}
          response={response}
          showSolution={showSolution}
          createAt={createAt}
        />
      </div>
    );
  }
}

export default NewExsercise;
