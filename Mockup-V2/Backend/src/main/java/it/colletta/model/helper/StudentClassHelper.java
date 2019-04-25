package it.colletta.model.helper;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class StudentClassHelper {
  List<String> studentsId;
  String classId;
}
