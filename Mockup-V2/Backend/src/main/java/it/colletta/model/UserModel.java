package it.colletta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor

@Document(collection = "users")
public class UserModel {

  @Id
  private String id;
  @JsonProperty("username")
  private String email;
  private String password;

  public UserModel(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public UserModel(UserModel userModel) {
    this.id = userModel.id;
    this.email = userModel.email;
    this.password = userModel.password;
  }

  @Override
  public String toString() {
    return String.format("Users[id=%s, email='%s', password='%s']", id, email, password);
  }

}
