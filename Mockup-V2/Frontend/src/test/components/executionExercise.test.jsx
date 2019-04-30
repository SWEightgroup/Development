import React from 'react';
import { mount } from 'enzyme';
import ExecutionExercise from '../../view/components/ExecutionExercise';
import Word from '../../view/components/Word';
import { removePunctuation } from '../../helpers/Utils';

describe('Testing executionExercise', () => {
  it('check render with any sentences', () => {
    const exercise = {
      user: {
        sentence: 'Questa è una frase qualsiasi : ; . Zebra ? カタ ',
        createAt: '1556528431800',
        language: 'it'
      }
    };
    const wrapper = mount(<ExecutionExercise exercise={exercise} />);

    expect(wrapper).not.toBe(null);
    // expect(wrapper).toMatchSnapshot();
  });

  it('create one button for each words', () => {
    const exercise = {
      user: {
        sentence: "Questa è una frase qualsiasi : ; . Zebra' a? カタ ",
        createAt: '1556528431800',
        language: 'it'
      }
    };
    const wrapper = mount(<ExecutionExercise exercise={exercise} />);

    const onlySentence = exercise.user.sentence;

    //  DA SOSTITUIRE CON QUELLA VERA
    const miaRemove = sentenceString => {
      const sentence = sentenceString;
      const allowedPunctuation = /[,.?!"'<->;:]/g;
      const cleanString = sentence
        .replace(allowedPunctuation, '')
        .replace(/[^\x20-\x7E]+/g, '')
        .replace(/(\s){2,}/g, '$1')
        .trim();
      return cleanString;
    };

    // cleanSentence is sentence without puntuaction
    const cleanSentence = miaRemove(onlySentence);

    const countWordsInSentence = str => {
      const count = str.match(/\S+/g).length;
      // const count = str.match(re).length;
      console.log('test', count);
      return count;
    };
    const numberOfWords = countWordsInSentence(cleanSentence);

    console.log('test', cleanSentence);
    console.log('test', onlySentence);
    console.log('test', numberOfWords);
    expect(wrapper.find(Word).length).toBe(1);
  });
});
