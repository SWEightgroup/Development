package it.colletta.resources;

public class SentenceModel {
  private final String text;

  /**
   * SentenceModle constructor.
   * @param text Sentence text.
   */
  public SentenceModel(String text) {
    this.text = text;
  }

  /**
   * Return the sentence text.
   * @return text Sentence text.
   */
  public String getText() {
    return this.text;
  }

}
