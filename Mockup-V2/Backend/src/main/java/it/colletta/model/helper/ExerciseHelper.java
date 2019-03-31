package it.colletta.model;

import lombok.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class ExerciseModel {
    @Id
    private String id;
    private Long dateExercise;
    @DBRef
    private PhraseModel phraseReference;
    private Boolean visibilty;
    private Boolean toDo;

    public ExerciseModel() {
        id = new ObjectId().toHexString();
        dateExercise = System.currentTimeMillis();
        phraseReference = new PhraseModel();
        visibilty = true;
        toDo = false;
    }
}