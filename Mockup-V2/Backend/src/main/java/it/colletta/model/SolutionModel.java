package it.colletta.model;

import java.util.Calendar;
import java.util.Date;

import lombok.*;
import org.bson.types.ObjectId;
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
    @Builder.Default
    private String id = new ObjectId().toHexString();
    private String solutionText;
    private Date dateSolution;
    private int affidability;
    private String authorId;
    private Double mark;

}
