package it.colletta.model.helper;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class UserLighterHelper {
  private String id;
  private String firstName;
  private String lastName;
  private String email;
}
