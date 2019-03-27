package it.colletta.model;

import java.util.Calendar;
import java.util.Date;
import org.springframework.data.annotation.Id;
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
@Document(collection = "solutions")
public class SolutionModel {
    @Id
    private String id;
    private String solutionText;
    private Date dateSolution;
    private int affidability;
    private String authorId;

    public SolutionModel() {
        dateSolution = Calendar.getInstance().getTime();
        affidability = 0;
    }

    public SolutionModel(String solutionText, String authorId) {
        this.solutionText = solutionText;
        this.authorId = authorId;
        dateSolution = Calendar.getInstance().getTime();
        affidability = 0;
    }
}
