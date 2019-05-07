package it.colletta.model.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CorrectionHelper {

  private String solutionFromStudent;
  private String exerciseId;

  /**
   * get solution from student.
   */
  public String getSolutionFromStudent() {
    return solutionFromStudent;
  }

  /**
   * get Exercise Id.
   */
  public String getExerciseId() {
    return exerciseId;
  }
}
