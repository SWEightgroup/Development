package it.colletta.model.helper;


import it.colletta.model.PhraseModel;
import it.colletta.model.SolutionModel;
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
    private List<String> classId;
    private List<String> userId;
    private PhraseModel phrase;
    private String teacherId;
    private Boolean visibility;
    /* private String phraseText;
    private String phraseTextSolution;
    private String language;
    private Boolean visibility;
    private String teacherId;        // the teacher mail */
}
