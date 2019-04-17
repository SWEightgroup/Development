import { _label } from '../constants/Label';

const _translator = (label, lang) => {
  const _lang = lang || 'it';
  try {
    if (
      _lang &&
      label &&
      _label[`${_lang}`] &&
      _label[`${_lang}`][`${label}`]
    ) {
      return `${_label[`${_lang}`][`${label}`]}`;
    }
    return `${label}`;
  } catch (err) {
    return label;
  }
};
export default _translator;
