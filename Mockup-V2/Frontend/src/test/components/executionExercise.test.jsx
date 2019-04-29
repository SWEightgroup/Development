import React from 'react';
import { mount } from 'enzyme';
import ExecutionExercise from '../../view/components/ExecutionExercise';

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
});
