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
public class TeacherClassesHelper{
  private List<UserModel> students;
  private String className;
  private String classId;
}
