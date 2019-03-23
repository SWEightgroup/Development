package it.colletta.model;

import java.text.SimpleDateFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "correction")
public class Correction {
    @Id
    private String correctionText;
    private SimpleDateFormat dateOfCreation;
    private float affidability;
    


    public void setCorrectonText(String correctionText){
        this.correctionText = correctionText;
    }

    public String getCorrectionTex(){
        return correctionText;
    }

    public void setDateOfCreation(SimpleDateFormat dateOfCreation){
        this.dateOfCreation = dateOfCreation;
    }

    public SimpleDateFormat getDateOfCreation(){
        return dateOfCreation;
    }

    public void setAffidability(float affidability){
        this.affidability = affidability;
    }

    public float getAffidability(){
        return affidability;
    }

}