package it.colletta.model.helper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CorrectionHelper {

  private String solutionFromStudent;
  private String exerciseId;

  /** @return */
  public String getSolutionFromStudent() {
    return solutionFromStudent;
  }

  /** @return */
  public String getExerciseId() {
    return exerciseId;
  }
}
