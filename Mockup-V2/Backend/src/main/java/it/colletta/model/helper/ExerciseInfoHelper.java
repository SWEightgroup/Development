package it.colletta.model.helper;

import it.colletta.model.SolutionModel;
import it.colletta.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
  private String language;
}
