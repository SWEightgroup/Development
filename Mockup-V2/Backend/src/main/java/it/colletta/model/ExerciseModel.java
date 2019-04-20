package it.colletta.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
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
  @Builder.Default
  private String alternativeSolutionId = null;
  private String authorName;
  private String authorId;
  private Boolean visibility;

  /**
   * Constructor.
   */
  public ExerciseModel() {
    this.id = new ObjectId().toHexString();
    this.dateExercise = System.currentTimeMillis();
    this.visibility = true;
    this.alternativeSolutionId = null;
  }
}
