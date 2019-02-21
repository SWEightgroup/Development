import React, { Component } from "react";

class EsecutionExercise extends Component {
  state = {};

  render() {
    console.log("props", this.props);

    return (
      <div className="row">
        <div className="col-6">
          <ul className="list-group">
            {this.props.phrase &&
              this.props.phrase.map((item, index) => {
                return (
                  <li key={"word-" + index} className="list-group-item">
                    {item}
                  </li>
                );
              })}
          </ul>
        </div>
      </div>
    );
  }
}

export default EsecutionExercise;
