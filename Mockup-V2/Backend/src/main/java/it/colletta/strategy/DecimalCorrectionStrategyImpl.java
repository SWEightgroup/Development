package it.colletta.strategy;

import java.util.ArrayList;
import java.util.Iterator;

public class DecimalCorrectionStrategyImpl<T extends Number, E extends Comparable<E>> implements CorrectionStrategy<T, E> {

  /**
   * Return the mark.
   * @param studentSolution List of student solutions
   * @param teacherSolution Main of alternative solution
   * @return mark expressed in a decimal format
   */
  @Override
  public T correction(ArrayList< E > studentSolution, ArrayList< E > teacherSolution) {
      int points = 0;
      if (studentSolution.size() == teacherSolution.size()) {
        Iterator<E> studentSolutionIterator = studentSolution.iterator();
        for (E word : teacherSolution) {
          E studentWord = studentSolutionIterator.next();
          if (word.compareTo(studentWord) == 0) {
            ++points;
          }
        }
      } else {
        throw new IllegalArgumentException("Solutions have different length");
      }
      int num = points * 10;
      int size = teacherSolution.size();
      Object t = ((double) num / (double) size);
      return (T)t;
    }
  }