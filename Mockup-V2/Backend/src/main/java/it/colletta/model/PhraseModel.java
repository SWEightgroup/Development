package it.colletta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "phrases")
public class PhraseModel {

  @Id private String id;

  @Indexed(unique = true)
  private String phraseText;

  @Builder.Default private ArrayList<SolutionModel> solutions = new ArrayList<>();
  private String language;
  private Long datePhrase;

  /** constructor. */
  public PhraseModel() {
    solutions = new ArrayList<>();
  }

  /** @param solutionModel */
  public void addSolution(final SolutionModel solutionModel) {
    solutions.add(solutionModel);
  }

  /**
   * @param solutionModel
   * @return
   */
  public boolean removeSolution(final SolutionModel solutionModel) {
    return solutions.remove(solutionModel);
  }
}
