import React, { Component } from "react";
import InputPhrase from "./InputPhrase";
import EsecutionExercise from "./ExecutionExercise";
class NewExsercise extends Component {
  state = {
    phrase: []
  };

  prepareExercise = phrase => {
    const phraseArray = phrase.split(" ");
    if (phraseArray.length > 0) {
      this.setState({ phrase: phraseArray });
    }
  };

  render() {
    return (
      <React.Fragment>
        <InputPhrase pippo={this.prepareExercise} />
        <EsecutionExercise phrase={this.state.phrase} />
      </React.Fragment>
    );
  }
}

export default NewExsercise;
