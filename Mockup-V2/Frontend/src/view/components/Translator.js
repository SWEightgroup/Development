import { _label } from '../../assets/lib/Label';

const _translator = (label, lang) => {
  lang = lang ? lang : 'it';
  try {
    if (lang && label && _label[`${lang}`] && _label[`${lang}`][`${label}`])
      return `${_label[`${lang}`][`${label}`]}`;

    console.error('LABEL NON TROVATA:', label);
    return `${label}`;
  } catch (err) {
    return label;
  }
};
export default _translator;
