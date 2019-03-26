package it.colletta.model;

import java.util.Calendar;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Document(collection = "solutions")
public class SolutionModel{
    @Id
    private String id;
    private String solutionText;
    private Date dateSolution;
    private Double affidability;

    public SolutionModel() {
        dateSolution = Calendar.getInstance().getTime();
        affidability = 0.0;
    }
}
