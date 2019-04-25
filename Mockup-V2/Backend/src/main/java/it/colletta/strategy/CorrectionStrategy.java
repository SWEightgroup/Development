package it.colletta.strategy;

import java.util.ArrayList;

public interface CorrectionStrategy<T, E> {

  T correction(ArrayList<E> studentSolution, ArrayList<E> teacherSolution);
}
