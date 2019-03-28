package it.colletta.model;

import java.util.Date;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
    private Date dateExercise;
    private String phraseReference;   
    private String author;
    private Boolean visibilty;


    @Transient
    private String textPhrase;
    @Transient
    private String textMainSolution;
    @Transient
    private String textAlternativeSolution;
    @Transient
    private String language;
}