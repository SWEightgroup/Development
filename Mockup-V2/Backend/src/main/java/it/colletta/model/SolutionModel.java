package it.colletta.model;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SolutionModel {

    @Id
    @Builder.Default
    private String id = new ObjectId().toHexString();
    private String solutionText;
    private Long dateSolution;
    private int affidability;
    private String authorId;
    private Double mark;

}
