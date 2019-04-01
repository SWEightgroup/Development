package it.colletta.model.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExerciseHelper {
    private List<String> assignedUsersIds;
    private String phraseText;
    private String mainSolution;
    private String alternativeSolution;
    private Boolean visibility;
    private String author;
    private Long date;
    private String language;
}
