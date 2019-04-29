package it.colletta.model.helper;


import it.colletta.model.UserModel;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class TeacherClasses{
  private List<UserLighterHelper> students;
  private String className;
  private String classId;
}
