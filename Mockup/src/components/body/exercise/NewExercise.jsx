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
      <div className="col-12 col-md-8">
        <InputPhrase pippo={this.prepareExercise} />
        <EsecutionExercise phrase={this.state.phrase} />
      </div>
    );
  }
}

export default NewExsercise;
