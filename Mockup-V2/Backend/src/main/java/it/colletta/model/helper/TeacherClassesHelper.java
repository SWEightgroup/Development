package it.colletta.model.helper;


import it.colletta.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class TeacherClassesHelper {
  private List<UserModel> students;
  private String className;
  private String classId;
}
