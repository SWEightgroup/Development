import React, { Component } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import InputSentence from '../components/InputSentence';
import ExecutionExercise from '../components/ExecutionExercise';

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

  salvaEsercizio = () => {
    console.log('Sto salvando');
    axios
      .post(`http://localhost:8081/sw/salvaEsercizio`, {
        token: this.props.token,
        text: this.state.sentenceString,
        soluzione: this.state.response
      })
      .then(res => {
        console.log('salvataggio avvenuto');
      })
      .catch(() => console.log('ERRORE DI SALVATAGGIO'));
  };

  /**
   * call the server to analyze the sentence
   */
  getSolution = () => {
    const { sentenceString } = this.state;
    console.log('CERCO LA SOLUZIONE');
    // qui faremo la chiamata
    // la soluzione sarà formata da un array di parola/codice
    axios
      .post(`http://localhost:8081/sw/s`, {
        text: sentenceString.trim()
      })
      .then(res => {
        console.log('SOLUZIONE TROVATA', res);

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
          salvaEsercizio={this.salvaEsercizio}
        />
      </div>
    );
  }
}

const mapStateToProps = store => {
  console.log(store);
  return {
    authError: store.auth.authError,
    auth: store.auth,
    token: store.auth.user.token
  };
};

export default connect(mapStateToProps)(NewExsercise);
