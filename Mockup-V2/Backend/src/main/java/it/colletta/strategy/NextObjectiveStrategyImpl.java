package it.colletta.strategy;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NextObjectiveStrategyImpl implements NextObjectiveStrategy<Double> {
    public Double nextProgress(Double input) {
      double number = Math.ceil(input / 10) * 10;
      return number;
    }
}
