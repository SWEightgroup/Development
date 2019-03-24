package it.colletta.model;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "phrases")
public class PhraseModel {
    @Id
    private String id;
    private String text;
    private ArrayList<String> correctionId;
    private String language;
}
