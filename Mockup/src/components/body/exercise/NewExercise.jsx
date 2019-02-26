import React, { Component } from "react";
import InputPhrase from "./InputPhrase";
import ExecutionExercise from "./ExecutionExercise";
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
        <InputPhrase prepareExercise={this.prepareExercise} />
        <ExecutionExercise phrase={this.state.phrase} />
      </div>
    );
  }
}

export default NewExsercise;
