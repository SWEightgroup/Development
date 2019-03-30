package it.colletta.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "exercises")
public class ExerciseModel {
    @Id
    private String id;
    private Long dateExercise;
    private String phraseReference;   
    private String author;
    private Boolean visibilty;
}