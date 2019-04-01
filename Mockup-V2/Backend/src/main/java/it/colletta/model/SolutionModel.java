package it.colletta.model;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.Builder.Default;

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
    @Builder.Default
    private Long dateSolution = System.currentTimeMillis();
    private int reliability;
    private String authorId;
    private Double mark;
}
