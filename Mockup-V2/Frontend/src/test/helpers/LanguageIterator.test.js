import LanguageIterator from '../../helpers/LanguageIterator';
import gerarchy from '../../constants/gerarchia';

it('LanguageIterator, getCodeSolution not null ', () => {
    const getCodeSolutionTest = new LanguageIterator(gerarchy).getBaseIterator().getCodeSolution();
    expect(getCodeSolutionTest).not.toBeNull();
  });
  it('LanguageIterator, getCodeSolution not null ', () => {
    const getCodeSolutionTest = new LanguageIterator(gerarchy).getBaseIterator().getVerboseSolution();
    expect(getCodeSolutionTest).not.toBeNull();
  });
  it('LanguageIterator, getCodeSolution not null ', () => {
    const getCodeSolutionTest = new LanguageIterator(gerarchy).getBaseIterator().getSolution();
    expect(getCodeSolutionTest).not.toBeNull();
  });