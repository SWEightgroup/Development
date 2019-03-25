package it.colletta.model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "corrections")
public class CorrectionModel {
    @Id
    private String id;
    private String correctionText;
    private Date dateOfCreation;
    private Double affidability;

    public CorrectionModel() {
        dateOfCreation = Calendar.getInstance().getTime();
    }

}
