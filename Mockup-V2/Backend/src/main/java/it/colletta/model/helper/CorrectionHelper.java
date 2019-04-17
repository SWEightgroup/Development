package it.colletta.model.helper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
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
