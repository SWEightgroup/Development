package resources;

public class SentenceModel {
  private final String text;
  
  /**
   * SentenceModle constructor.
   * @param text
   */
  public SentenceModel(String text){
    this.text = text;
  }
  
  /**
   * Return the sentence text
   * @return text
   */
  public String getText() {
    return this.text;
  }
  
}
