package it.colletta.model.helper;


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
public class ClassHelper {
  private String classId;
  private String name;
  private List<String> studentsId;
  private String teacherId;

}
