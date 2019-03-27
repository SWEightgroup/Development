import React, { Component } from 'react';
import PropTypes from 'prop-types';

class ExercisePreview extends Component {
  state = {};
  render() {
    const {
      author,
      creationDate,
      executionDate,
      phrase,
      mark,
      isMark
    } = this.props;
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
}

ExercisePreview.propTypes = {
  author: PropTypes.string.isRequired,
  creationDate: PropTypes.instanceOf(Date).isRequired,
  executionDate: PropTypes.instanceOf(Date), //could also not exist
  phrase: PropTypes.string.isRequired,
  mark: PropTypes.string,
  isMark: PropTypes.bool.isRequired
};

export default ExercisePreview;

/* 
https://blog.logrocket.com/validating-react-component-props-with-prop-types-ef14b29963fc
PropTypes.any — the prop can be of any data type
PropTypes.bool — the prop should be a boolean
PropTypes.number — the prop should be a number
PropTypes.string — the prop should be a string
PropTypes.func — the prop should be a function
PropTypes.array — the prop should be an array
PropTypes.object — the prop should be an object
PropTypes.symbol — the prop should be a symbol
PropTypes.node — the prop should be anything that can be rendered by React: number, string, element or an array (or fragment) containing these types
PropTypes.element — the prop should be a React element
PropTypes.oneOf — the prop is limited to a specified set of values, treating it like an enum
PropTypes.oneOfType — the prop should be one of a specified set of types, behaving like a union of types 

https://codeburst.io/validating-props-easily-with-react-proptypes-96e80208207  

mark: PropTypes.shape({
    title: PropTypes.string.isRequired,
    date: PropTypes.instanceOf(Date).isRequired,
    isRecent: PropTypes.bool
  }).isRequired*/
