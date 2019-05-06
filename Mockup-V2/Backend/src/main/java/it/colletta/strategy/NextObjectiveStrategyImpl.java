package it.colletta.strategy;

public class NextObjectiveStrategyImpl implements NextObjectiveStrategy<Double> {

  public Double nextProgress(Double input) {
    double number = Math.ceil(input / 10) * 10;
    return number;
  }
}
