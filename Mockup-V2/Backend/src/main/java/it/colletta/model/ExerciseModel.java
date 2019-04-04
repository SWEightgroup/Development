package it.colletta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
  @DBRef(lazy = true)
  private SolutionModel mainSolutionReference;
  @DBRef(lazy = true)
  private SolutionModel alternativeSolutionReference;
  private String authorName;
  private String authorId;
  private Boolean visibility;

  public ExerciseModel() {
    id = new ObjectId().toHexString();
    dateExercise = System.currentTimeMillis();
    visibility = true;
  }
}