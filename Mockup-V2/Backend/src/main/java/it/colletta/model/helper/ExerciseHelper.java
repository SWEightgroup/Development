package it.colletta.model.helper;

import lombok.*;

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
  private String author;
  private Long date;
  private String language;
  // TODO ho aggiunto questo campo per aggiungere l'esercizio alla lista degli esercizi svolti
  // dell'utente
  // se non vi piace, trovate un'altra soluzione
  private String id;
}
