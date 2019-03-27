package it.colletta.model;

/*
* This class is used to manage the signup request from user
* */

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Getter
@Document(collection = "singuprequests")
public class SignupRequestModel {
    @Id
    private String id;
    private String token;
    private Date requestDate;
    private String userReference;
}
