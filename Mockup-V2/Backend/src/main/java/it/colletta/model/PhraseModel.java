package it.colletta.model;

import java.sql.Date;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "phrases")
public class PhraseModel {
    @Id
    private String id;
    @Indexed(unique = true)
    private String phraseText;
    private ArrayList<SolutionModel> solutions; 
    private String language;
    private Date datePhrase;
}
