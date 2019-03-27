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
    @Id
    private String id;
    private String solutionText;
    private Date dateSolution;
    private int affidability;
    private String authorId;


    public SolutionModel(String solutionText, String authorId) {
        id=null;
        this.solutionText = solutionText;
        this.authorId = authorId;
        dateSolution = Calendar.getInstance().getTime();
        affidability = 0;
    }
}
