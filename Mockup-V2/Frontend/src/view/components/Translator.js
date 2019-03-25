import { _label } from '../../assets/lib/Label';
import { store } from '../../index';

export const _translator = label => {
  const lang = store.getState().auth.lang;

  try {
    if (lang && label && _label[`${lang}`] && _label[`${lang}`][`${label}`])
      return `${_label[`${lang}`][`${label}`]}`;

    console.error('LABEL NON TROVATA:', label);
    return `${label}`;
  } catch (err) {}
};
