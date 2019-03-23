package it.colletta.model;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "phrases")
public class Phrase {
    @Id
    private String id;
    private ArrayList<String> correctionId;
    private String language;

    public String getId() {
        return id;
    }

    public ArrayList<String> getCorrections() {
        return correctionId;
    }

    public String getLanguage() {
        return language;
    }

}
