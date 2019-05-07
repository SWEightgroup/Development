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
      int sizeList = Math.min(teacherSolution.size(), studentSolution.size());
      for (int i = 0; i < sizeList; ++i) {
        if (studentSolution.get(i).compareTo(teacherSolution.get(i)) == 0) {
          ++points;
        }
      }
      int num = points * 10;
      int size = teacherSolution.size();
      Object t = ((double) num / (double) size);
      return (T)t;
    }
  }