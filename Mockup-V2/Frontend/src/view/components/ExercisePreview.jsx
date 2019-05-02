import React, { Component } from 'react';
import _translator from '../../helpers/Translator';

// import PropTypes from 'prop-types';

class ExercisePreview extends Component {
  state = {};

  getDate = timestamp => {
    const date = new Date(timestamp);
    return [date.getDate(), date.getMonth() + 1, date.getFullYear()].join('/');
  };

  goToExecution = () => {
    const { phrase, selectExercise, id } = this.props;

    selectExercise(phrase, id);
  };

  onSelect = () => {
    const { selectCard, id } = this.props;
    if (selectCard) selectCard(id);
  };

  render() {
    const { author, creationDate, phrase, mark, isMark, language } = this.props;
    let markClass = 'alert-danger';
    if (mark && mark > 8) markClass = 'alert-success';
    if (mark && mark <= 8 && mark > 5) markClass = 'alert-warning';
    return (
      <div className="main-card mb-3 card exercise-preview">
        <div className="card-body" onClick={this.onSelect}>
          <div className="row">
            <div className="col">
              <h5 className="card-title">
                <small>{_translator('gen_phrase', language)}: </small>
                {phrase}
              </h5>
              <h6 className="card-subtitle">
                <small>{_translator('gen_author', language)}: </small> {author}
              </h6>
              <h6 className="card-subtitle">
                <small>
                  {_translator('exercisePreview_addedOn', language)}:
                </small>
                {this.getDate(creationDate)}
              </h6>
            </div>
            {isMark && mark && (
              <div className="col-md-auto">
                <div className={`alert pull-right ${markClass}`} role="alert">
                  {mark}/10
                </div>
              </div>
            )}
          </div>
          {!isMark && (
            <button
              type="button"
              className="mb-2 mr-2 btn btn-primary btn-sm btn-block "
              onClick={this.goToExecution}
            >
              {_translator('exercisePreview_execute', language)}
            </button>
          )}
        </div>
      </div>
    );
  }
}

/* ExercisePreview.propTypes = {
  author: PropTypes.string.isRequired,
  creationDate: PropTypes.instanceOf(Date).isRequired,
  executionDate: PropTypes.instanceOf(Date), // could also not exist
  phrase: PropTypes.string.isRequired,
  mark: PropTypes.string,
  isMark: PropTypes.bool.isRequired
}; */

export default ExercisePreview;
