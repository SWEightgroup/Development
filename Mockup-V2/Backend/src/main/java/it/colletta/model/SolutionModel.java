package it.colletta.model;

import java.util.Calendar;
import java.util.Date;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "solutions")
public class SolutionModel {
    private String solutionText;
    private Long dateSolution;
    private int affidability;
    private String authorId;


    public SolutionModel(String solutionText, String authorId) {
        this.solutionText = solutionText;
        this.authorId = authorId;
        dateSolution = System.currentTimeMillis();
        affidability = 0;
    }
}
