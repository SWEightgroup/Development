package it.colletta.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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

  public ExerciseModel() {
    id = new ObjectId().toHexString();
    dateExercise = System.currentTimeMillis();
    visibility = true;
  }
  

}