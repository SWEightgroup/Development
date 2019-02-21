import React, { Component } from "react";

class InputPhrase extends Component {
  state = {
    phrase: ""
  };

  handleChange = e => {
    this.setState({ [e.target.id]: e.target.value });
  };

  handleSubmit = e => {
    e.preventDefault();
    this.props.prepareExercise(this.state.phrase);
  };

  render() {
    return (
      <div className="row">
        <div className="col-md-6">
          <div className="main-card mb-3 card">
            <div className="card-body">
              <h5 className="card-title">Input Group Button Shorthand</h5>

              <form onSubmit={this.handleSubmit} className="needs-validation was-validated">
                <div className="input-group">
                  <input
                    id="phrase"
                    type="text"
                    className="form-control validate"
                    placeholder="Inserisci una frase"
                    onChange={this.handleChange}
                    required
                  />
                  <div className="input-group-append">
                    <button className="btn btn-success" type="submit">
                      SVOLGI ESERCIZIO
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default InputPhrase;
