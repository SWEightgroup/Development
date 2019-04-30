package it.colletta.model.helper;

public class ProgressHelper {

  private Double currentLevel;
  private Double nextLevel;


  public ProgressHelper() {
    this.currentLevel = .0;
    this.nextLevel = .0;
  }
  public ProgressHelper(Double currentLevel, Double nextLevel) {
    this.currentLevel = currentLevel;
    this.nextLevel = nextLevel;
  }

}
