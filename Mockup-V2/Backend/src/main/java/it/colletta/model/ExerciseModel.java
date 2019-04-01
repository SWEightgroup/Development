package it.colletta.model;

import lombok.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ExerciseModel {
    @Id
    @Builder.Default
    private String id = new ObjectId().toHexString();;
    private Long dateExercise;
    /*@DBRef
    private PhraseModel phraseReference;*/
    private String phraseId;
    private String phraseText;
    @DBRef(lazy = true)
    private SolutionModel mainSolutionReference;
    @DBRef(lazy = true)
    private SolutionModel alternativeSolutionReference;
    private String TeacherName;

    private Boolean visibilty;
    private Boolean toDo;

    public ExerciseModel() {
        id = new ObjectId().toHexString();
        dateExercise = System.currentTimeMillis();
        visibilty = true;
        toDo = false;
    }
}