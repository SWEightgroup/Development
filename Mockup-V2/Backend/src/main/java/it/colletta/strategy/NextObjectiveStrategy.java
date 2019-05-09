package it.colletta.strategy;

@FunctionalInterface
public interface NextObjectiveStrategy<T> {
  T nextProgress(T input);
}
