import React from 'react';
import { shallow } from 'enzyme';
import User from '../../view/components/User';




describe('Testing User component', () => {
  it('render all user info', () => {
    const props = {
      firstName: 'TestName',
      lastName: 'TestLastName',
      username: 'TestEmail',
      language: 'it',
      btAction: jest.fn(),
      role: 'ROLE_STUDENT',
      id: '12345456667'
    };

    const wrapper = shallow(<User {...props} />);
    expect(wrapper.find('div.widget-heading').length).toBe(1);

    expect(
      wrapper.contains(
        <div className="widget-heading">TestName TestLastName</div>
      )
    ).toEqual(true);

    expect(
      wrapper.contains(<td className="text-center">TestEmail</td>)
    ).toEqual(true);

    expect(wrapper.contains(<td className="text-center">Studente</td>)).toEqual(
      true
    );
  });

  it('function is called when button is pressed', () => {
    const btAction = jest.fn();

    const props = {
      firstName: 'TestName',
      lastName: 'TestLastName',
      username: 'TestEmail',
      language: 'it',
      btAction,
      role: 'ROLE_STUDENT',
      id: '12345456667'
    };

    const wrapper = shallow(<User {...props} />);
    const button = wrapper.find('button');
    expect(button.length).toBe(1); // it finds it alright
    button.simulate('click');
    expect(btAction).toHaveBeenCalled();
  });

  /*
  it('save button is labeled "Saving..." when boolProp is true', () => {
    const wrapper = setup(true);
    expect(wrapper.find('input').props().value).toBe('Saving...');
  }); */
});
