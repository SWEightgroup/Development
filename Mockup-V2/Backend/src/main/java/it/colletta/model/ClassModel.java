package it.colletta.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "classes")
public class ClassModel {

  @Id
  private String id;
  private String name;
  private List<String> studentsId;
  private String teacherId;

  /**
   * The method is used to change the name of a class
   *
   * @param name the name of the class
   */
  public void setName(final String name) {
    this.name = name;
  }

  /*
   * public void addStudent(@NonNull UserModel student) { studentList.add(student); }
   */
  public void setTeacherId(final String teacherId) {
    this.teacherId = teacherId;
  }
  /*
   * public Boolean deleteStudent(@NonNull UserModel student){ if(studentList.contains(student)){
   * return studentList.remove(student); } else { return false; } }
   */

  /**
   * @param studentsId the List of studentsId present in the class
   */
  public void setStudentsId(List<String> studentsId) {
    this.studentsId = studentsId;
  }
}
