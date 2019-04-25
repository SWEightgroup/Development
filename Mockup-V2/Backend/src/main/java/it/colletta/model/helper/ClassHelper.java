package it.colletta.model.helper;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class ClassHelper {
  private String classId;
  private String name;
  private List<String> studentsId;
  private String teacher;
  private String teacherId;
}
