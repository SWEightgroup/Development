package it.colletta.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
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