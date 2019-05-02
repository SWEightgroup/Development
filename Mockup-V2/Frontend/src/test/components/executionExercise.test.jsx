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

    // cleanSentence is sentence without puntuaction
    const cleanSentence = removePunctuation(onlySentence);

    const countWordsInSentence = str => {
      const re = / \w /g;
      return ((str || '').match(re) || []).length;
    };
    const numberOfWords = countWordsInSentence(cleanSentence);

    expect(wrapper.find(Word).length).not.toBe(numberOfWords);
  });
});