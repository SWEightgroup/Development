import _translator from '../../helpers/Translator';
// need to install jest-extended!!!!!!!!
// import toBeString from 'jest-extended';

it('traslator output is null and is string ', () => {
  const lang = 'it';

  let test = _translator('gen_role', lang);
  expect(test).toMatch('Ruolo');

  test = _translator('ewirnnfpweroquii', lang);
  expect(test).not.toBe(null);
  // expect(prova).toBeString();
});
