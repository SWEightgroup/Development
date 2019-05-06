import React, { Component } from 'react';
import _translator from '../../helpers/Translator';
import { removePunctuation } from '../../helpers/Utils';
// import { ExLang } from '../../constants/Languages';

class InputSentence extends Component {
  handleChange = e => {
    e.preventDefault();
    this.props.changeNewInputSentence({ [e.target.id]: e.target.value });
  };

  handleSubmit = e => {
    e.preventDefault();
    const { sentenceString } = this.props;
    const cleanString = removePunctuation(sentenceString);
    const { prepareExercise } = this.props;

    prepareExercise(cleanString);
  };

  render() {
    const { language, sentenceString /* exLanguage */ } = this.props;
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
              {/*
              <div className="input-group-prepend no-validated">
                <select
                  name="language"
                  className="px-3"
                  id="languageSelected"
                  onChange={this.handleChange}
                  value={exLanguage}
                >
                  {ExLang.map(lang => (
                    <option key={`lang-${lang}`} value={lang}>
                      {_translator(`gen_${lang}`, language)}
                    </option>
                  ))}
                </select>
              </div>
              */}
              <input
                id="sentenceString"
                type="text"
                className="form-control"
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
