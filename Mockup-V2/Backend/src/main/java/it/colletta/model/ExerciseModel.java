package it.colletta.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Builder
@ToString
@Document(collection = "exercises")
@JsonInclude(Include.NON_NULL)
public class ExerciseModel {

  @Id
  @Builder.Default
  private String id = new ObjectId().toHexString();
  private Long dateExercise;
  private String phraseId;
  private String phraseText;
  private String mainSolutionId;
  private String language;
  private String pippo;
  @Builder.Default
  private String alternativeSolutionId = null;
  private String authorName;
  private String authorId;
  private Boolean visibility;
  @Builder.Default
  private ArrayList<String> studentIdToDo = new ArrayList<>();
  @Builder.Default
  private ArrayList<String> studentIdDone = new ArrayList<>();

  /**
   * get id .
   */
  public String getId() {
    return id;
  }
  /**
   * get DateExercise.
   */
  public Long getDateExercise() {
    return dateExercise;
  }
  /**
   * get PhraseId.
   */
  public String getPhraseId() {
    return phraseId;
  }
  /**
   * get PhraseText.
   */
  public String getPhraseText() {
    return phraseText;
  }
  /**
   * get MainSolutionId.
   */
  public String getMainSolutionId() {
    return mainSolutionId;
  }
  /**
   * get AlternativeSolutionId.
   */
  public String getAlternativeSolutionId() {
    return alternativeSolutionId;
  }
  /**
   * get lternativeSolutionId.
   */
  public Optional<String> getAlternativeSolutionIdOptional() {
    return Optional.ofNullable(alternativeSolutionId);
  }
  /**
   * get AuthorName.
   */
  public String getAuthorName() {
    return authorName;
  }
  /**
   * get AuthorId.
   */
  public String getAuthorId() {
    return authorId;
  }
  /**
   * get Visibility.
   */
  public Boolean getVisibility() {
    return visibility;
  }
  /**
   * get tudentIdToDo.
   */
  public ArrayList<String> getStudentIdToDo() {
    return studentIdToDo;
  }
  /**
   * get StudentIdDone.
   */
  public ArrayList<String> getStudentIdDone() {
    return studentIdDone;
  }

  /**
   * Constructor.
   */
  public ExerciseModel() {
    this.id = new ObjectId().toHexString();
    this.dateExercise = System.currentTimeMillis();
    this.visibility = true;
    this.alternativeSolutionId = null;
    this.studentIdToDo = new ArrayList<>();
    this.studentIdDone = new ArrayList<>();
  }
  /**
   * set DateExercise.
   */
  public void setDateExercise(Long dateExercise) {
    this.dateExercise = dateExercise;
  }
  /**
   * set PhraseId.
   */
  public void setPhraseId(String phraseId) {
    this.phraseId = phraseId;
  }
  /**
   * set PhraseText.
   */
  public void setPhraseText(String phraseText) {
    this.phraseText = phraseText;
  }
  /**
   * set MainSolutionId.
   */
  public void setMainSolutionId(String mainSolutionId) {
    this.mainSolutionId = mainSolutionId;
  }
  /**
   * set AlternativeSolutionId.
   */
  public void setAlternativeSolutionId(String alternativeSolutionId) {
    this.alternativeSolutionId = alternativeSolutionId;
  }
  /**
   * set AuthorName.
   */
  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }
  /**
   * set AuthorId.
   */
  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }
  /**
   * set Visibility.
   */
  public void setVisibility(Boolean visibility) {
    this.visibility = visibility;
  }
  /**
   * set StudentIdToDo.
   */
  public void setStudentIdToDo(ArrayList<String> studentIdToDo) {
    this.studentIdToDo = studentIdToDo;
  }
  /**
   * set StudentToDoIds.
   */
  public boolean addStudentToDoIds(List<String> ids) {
    return studentIdToDo.addAll(ids);
  }
  /**
   * set Language.
   */
  public String getLanguage() {
    return this.language;
  }
}
