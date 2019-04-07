package it.colletta.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SolutionModel {

  @Id
  @Builder.Default
  private String id = new ObjectId().toHexString();
  private String solutionText;
  @Builder.Default
  private Long dateSolution = System.currentTimeMillis();
  private int reliability;
  private String authorId;
  private Double mark;
}
