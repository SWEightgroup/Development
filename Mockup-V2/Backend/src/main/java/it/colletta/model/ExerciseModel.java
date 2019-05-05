package it.colletta.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
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

  public String getId() {
    return id;
  }

  public Long getDateExercise() {
    return dateExercise;
  }

  public String getPhraseId() {
    return phraseId;
  }

  public String getPhraseText() {
    return phraseText;
  }

  public String getMainSolutionId() {
    return mainSolutionId;
  }

  public String getAlternativeSolutionId() {
    return alternativeSolutionId;
  }

  public Optional<String> getAlternativeSolutionIdOptional() {
    return Optional.ofNullable(alternativeSolutionId);
  }

  public String getAuthorName() {
    return authorName;
  }

  public String getAuthorId() {
    return authorId;
  }

  public Boolean getVisibility() {
    return visibility;
  }

  public ArrayList<String> getStudentIdToDo() {
    return studentIdToDo;
  }

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

  public void setDateExercise(Long dateExercise) {
    this.dateExercise = dateExercise;
  }

  public void setPhraseId(String phraseId) {
    this.phraseId = phraseId;
  }

  public void setPhraseText(String phraseText) {
    this.phraseText = phraseText;
  }

  public void setMainSolutionId(String mainSolutionId) {
    this.mainSolutionId = mainSolutionId;
  }

  public void setAlternativeSolutionId(String alternativeSolutionId) {
    this.alternativeSolutionId = alternativeSolutionId;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public void setVisibility(Boolean visibility) {
    this.visibility = visibility;
  }

  public void setStudentIdToDo(ArrayList<String> studentIdToDo) {
    this.studentIdToDo = studentIdToDo;
  }

  public boolean addStudentToDoIds(List<String> ids) {
    return studentIdToDo.addAll(ids);
  }

  public String getLanguage() {
    return this.language;
  }
}
