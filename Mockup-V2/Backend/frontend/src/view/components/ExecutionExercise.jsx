import React, { Component } from 'react';
import Word from './Word';
import gerarchia from '../../constants/gerarchia';
import _translator from '../../helpers/Translator';

class ExecutionExercise extends Component {
  extractTag = (response, index) => {
    return response[index].tag;
  };

  render() {
    // const allowedPunctuation = /[a-zA-Z]/g;
    // const allowedPunctuation2 = /[,.?!"'<-{}[]()%\/>;:]/g;
    const { props } = this;
    const {
      sentence,
      showSolution,
      initSolution,
      createAt,
      language,
      showButton,
      indexSolution
      // exLanguage
    } = this.props;

    const filterWord = sentence; // .filter(word => word.match(allowedPunctuation));

    const tags = props.response
      ? props.response.filter(word => word.tag.charAt(0) !== 'F')
      : null;
    if (sentence && sentence.length) {
      return (
        <div className="main-card mb-3 card">
          <div className="card-body">
            <h5 className="card-title">
              {_translator('executionExercise_completeExercise', language)}
            </h5>
            <ul className="list-group">
              {filterWord &&
                filterWord.map((item, index) => {
                  return (
                    <Word
                      // exLanguage={exLanguage}
                      key={`${index + item + createAt}word`}
                      parola={item}
                      gerarchy={gerarchia}
                      index={index}
                      solutionTag={
                        props.response && props.response.length
                          ? this.extractTag(tags, index)
                          : null
                      }
                      showSolution={showSolution}
                      showButton={showButton}
                      initSolution={initSolution}
                      indexSolution={indexSolution}
                    />
                  );
                })}
            </ul>
          </div>
        </div>
      );
    }
    return <React.Fragment />;
  }
}

export default ExecutionExercise;
