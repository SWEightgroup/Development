package it.colletta.model.helper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProgressHelper {

  private Double currentLevel;
  private Double nextLevel;

  /**
   * Class constructor.
   */
  public ProgressHelper() {
    this.currentLevel = .0;
    this.nextLevel = .0;
  }

  /**
   * Class constructor.
   */
  public ProgressHelper(Double currentLevel, Double nextLevel) {
    this.currentLevel = currentLevel;
    this.nextLevel = nextLevel;
  }

}
