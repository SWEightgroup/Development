import _label from '../../constants/Label';
import _translator from '../../helpers/Translator';
import toBeString from 'jest-extended';

// no method to match type of return.
// Just check the return is not null.
it('traslator output is null and is string ', () => {
  const lang = 'it';
  //   const label = _label;

  let prova = _translator('gen_role', lang);
  expect(prova).toMatch('Ruolo');

  prova = _translator('ewirnnfpweroquii', lang);
  expect(prova).not.toBe(null);
  expect(prova).toBeString();
});
