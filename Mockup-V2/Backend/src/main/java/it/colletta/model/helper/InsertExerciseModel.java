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
public class InsertExerciseModel{
    private List<String> assignedUsersIds;
    private String authorId;
    private Boolean visibility;
    private String textPhrase;
    private String textMainSolution;
    private String textAlternativeSolution;
    private String language;  
}
