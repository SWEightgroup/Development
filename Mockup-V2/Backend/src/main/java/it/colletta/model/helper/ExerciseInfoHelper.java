package it.colletta.model.helper;

import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;
import it.colletta.service.SolutionService;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExerciseInfoHelper {
  private String exerciseText;
  private String authorName;
  private Date date;
  private SolutionModel mainSolution;
  private SolutionModel alternativeSolution;
  private List<UserModel> studentToDo;
  private List<UserModel> studentDone;
}
