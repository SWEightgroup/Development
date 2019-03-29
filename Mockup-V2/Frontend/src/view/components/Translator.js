import { _label } from '../../assets/lib/Label';
import { store } from '../../index';

const _translator = label => {
  const { user } = store.getState().auth;

  const lang = user ? user.language : 'it';
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
