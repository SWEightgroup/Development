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
  private SolutionModel mainSolution;
  private SolutionModel alternativeSolution;
  private String exerciseText;
  private String authorName;
  private Date date;
  private List<UserModel> studentToDo;
  private List<UserModel> studentDone;
}
