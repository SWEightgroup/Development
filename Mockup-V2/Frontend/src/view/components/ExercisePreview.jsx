import React, { Component } from 'react';
// import PropTypes from 'prop-types';

class ExercisePreview extends Component {
  state = {};

  render() {
    const { author, creationDate, phrase, mark, isMark } = this.props;
    return (
      <div className="main-card mb-3 card">
        <div className="card-body">
          <h5 className="card-title">{author}</h5>
          <h6 className="card-subtitle">{phrase}</h6>
          <p>Aggiunta il: {this.getDate(creationDate)}</p>
          {!isMark && (
            <button className="mb-2 mr-2 btn btn-primary btn-sm btn-block ">
              Svolgi l'esercizio
            </button>
          )}
          {isMark && <p className=" ">{mark}</p>}
        </div>
      </div>
    );
  }

  getDate(date) {
    return [date.getDate(), date.getMonth() + 1, date.getFullYear()].join('/');
  }

  goToExecution() {
    // CARICARE NELLO STORE L'ID DELLA SOLUZIONE (VA AGGIUNTO NELLE PROPS)
    // REINDIRIZZARE L'UTENTE ALLA PAGINA DI ESECIZIONE DELL'ESERICIZIO (SI PUO ANCHE PRENDERE L'ID DAL PATH INVECE CHE METTERLO NELLO STORE)
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
