package it.colletta.model;

import java.text.SimpleDateFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "corrections")
public class Correction {
    @Id
    private String id;
    private String text;
    private SimpleDateFormat dateOfCreation;
    private Double affidability;
}
