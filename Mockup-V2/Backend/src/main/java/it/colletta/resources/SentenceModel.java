package it.colletta.resources;

public class SentenceModel {
  public String text = "";
  public String token ="";
  public Object soluzione = new Object();
  /**
   * SentenceModle constructor.
   * @param text Sentence text.
   */
  /* public SentenceModel(String text) {
    this.text = text;
  }*/

  /**
   * Return the sentence text.
   * @return text Sentence text.
   */
  public String getText() {
    return this.text;
  }

}
