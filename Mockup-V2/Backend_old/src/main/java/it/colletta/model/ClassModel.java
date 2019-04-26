package it.colletta.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
   * @param name TODO
   * @return TODO
   */
  public void setName(final String name) {
    this.name = name;
  }
  /*
   * public void addStudent(@NonNull UserModel student) { studentList.add(student); }
   */
  public void setTeacherId(final String teacherId){this.teacherId = teacherId;}
  /**
   * @param student TODO
   * @return TODO
   */
  /*
   * public Boolean deleteStudent(@NonNull UserModel student){ if(studentList.contains(student)){
   * return studentList.remove(student); } else { return false; } }
   */

  /**
   * @param studentsId the List of studentsId present in the class
   */
  public void setStudentsId(List<String> studentsId){
    this.studentsId = studentsId;
  }
}
