package it.colletta.model.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ExerciseHelper {
  private List<String> assignedUsersIds;
  private String phraseText;
  private String mainSolution;
  private String alternativeSolution;
  private Boolean visibility;
  private Boolean visibilityDev;
  private String author;
  private Long date;
  private String language;
  private String id;
}
