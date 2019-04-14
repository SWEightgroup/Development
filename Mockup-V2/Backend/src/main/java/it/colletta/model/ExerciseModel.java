package it.colletta.model;

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
public class ExerciseModel {

  @Id
  @Builder.Default
  private String id = new ObjectId().toHexString();
  private Long dateExercise;
  private String phraseId;
  private String phraseText;
  private String mainSolutionId;

  private String alternativeSolutionId;
  private String authorName;
  private String authorId;
  private Boolean visibility;

  /** Constructor. */
  public ExerciseModel() {
    id = new ObjectId().toHexString();
    dateExercise = System.currentTimeMillis();
    visibility = true;
  }
}
