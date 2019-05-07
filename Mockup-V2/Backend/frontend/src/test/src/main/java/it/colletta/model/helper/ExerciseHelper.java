package it.colletta.model.helper;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
  private String author;
  private Long date;
  private String language;
  private String id;
}
