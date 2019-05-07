// 3 PASSED : test if the methods in solutionMapper is not null
import SolutionMapper from '../../helpers/SolutionMapper';
import gerarchy from '../../constants/gerarchia';

it('solutionMapper, getCodeSolution not null ', () => {
  const solutionTag = 'NCC00FS';
  const getCodeSolutionTest = new SolutionMapper(
    solutionTag,
    gerarchy.it
  ).getCodeSolution();
  expect(getCodeSolutionTest).not.toBeNull();
});

it('solutionMapper, getVerboseSolution not null', () => {
  const solutionTag = 'NCC00FS';

  const getVerboseSolutionTest = new SolutionMapper(
    solutionTag,
    gerarchy.it
  ).getVerboseSolution();
  expect(getVerboseSolutionTest).not.toBeNull();
});

it('solutionMapper, getSolutionTest not null', () => {
  const solutionTag = 'NCC00FS';

  const getSolutionTest = new SolutionMapper(
    solutionTag,
    gerarchy.it
  ).getSolution();

  expect(getSolutionTest).not.toBeNull();
});
