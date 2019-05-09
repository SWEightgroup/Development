package it.colletta.model.helper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordHelper {
  String requestId;
  String password;
  String passwordConfirm;
  String username;
}
