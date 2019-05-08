package it.colletta.model;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "forgotpassword")
public class ForgotPasswordModel {

  @Id
  private String id;
  private String userId;
  @Field
  @Indexed(name = "requestDate", expireAfterSeconds = 86400)
  private Date requestDate;
}
