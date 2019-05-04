import React from 'react';
import PropTypes from 'prop-types';

const Checkbox = ({ type, name, checked, onChange, id }) => (
  <input
    type={type}
    name={name}
    checked={checked}
    onChange={onChange}
    id={id}
  />
);

Checkbox.propTypes = {
  type: PropTypes.string,
  name: PropTypes.string.isRequired,
  checked: PropTypes.bool,
  onChange: PropTypes.func.isRequired,
  id: PropTypes.string.isRequired
};

Checkbox.defaultProps = {
  type: 'checkbox',
  checked: false
};
export default Checkbox;
