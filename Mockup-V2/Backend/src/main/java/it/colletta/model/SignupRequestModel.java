package it.colletta.model;

import lombok.Builder;
import lombok.Getter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


/**
 * This class is used to manage the signup request from user
 */
@Builder
@Getter
@Document(collection = "singuprequests")
public class SignupRequestModel {

  @Id
  private String id;
  private String token;

  @Indexed(name = "Time To Live", expireAfterSeconds = 86400)
  private Date requestDate;
  private UserModel userToConfirm;
}
