package it.colletta.model;

import java.text.SimpleDateFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "corrections")
public class Correction {
    @Id
    private String id;
    private String correctionText;
    private SimpleDateFormat dateOfCreation;
    private Double affidability;


    public void setCorrectonText(String correctionText) {
        this.correctionText = correctionText;
    }

    public String getCorrectionTex() {
        return correctionText;
    }

    public void setDateOfCreation(SimpleDateFormat dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public SimpleDateFormat getDateOfCreation() {
        return dateOfCreation;
    }

    public void setAffidability(Double affidability) {
        this.affidability = affidability;
    }

    public Double getAffidability() {
        return affidability;
    }

}
