import React, { Component } from 'react';
import _translator from '../../helpers/Translator';
import { removePunctuation } from '../../helpers/Utils';

class InputSentence extends Component {
  handleChange = e => {
    e.preventDefault();
    this.props.changeNewInputSentence(e.target.value);
  };

  handleSubmit = e => {
    e.preventDefault();
    const { sentenceString } = this.props;
    const cleanString = removePunctuation(sentenceString);
    const { prepareExercise } = this.props;

    prepareExercise(cleanString);
  };

  render() {
    const { language, sentenceString } = this.props;
    return (
      <div className="main-card mb-3 card">
        <div className="card-body">
          <h5 className="card-title ">
            {_translator('inputSentence_insertSentence', language)}
          </h5>

          <form
            onSubmit={this.handleSubmit}
            className="needs-validation was-validated"
          >
            <div className="input-group">
              <input
                id="sentenceString"
                type="text"
                className="form-control validate"
                placeholder={_translator(
                  'inputSentence_insertSentence',
                  language
                )}
                onChange={this.handleChange}
                required
                value={sentenceString}
              />
              <div className="input-group-append">
                <button className="btn btn-success" type="submit">
                  {_translator('inputSentence_executeExercise', language)}
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    );
  }
}

export default InputSentence;
