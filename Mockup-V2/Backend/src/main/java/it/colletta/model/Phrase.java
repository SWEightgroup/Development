package it.colletta.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "phrase")
public class Phrase {
    @Id
    private String id;
    private ArrayList<String> CorrectionId;
    private String language;

    public String getId(){
        return id;
    }

    public ArrayList<String> getCorrections(){
        return CorrectionId;
    }

    public String getLanguage(){
        return language;
    }

}