package it.colletta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(
    collection = "classes")
public class ClassModel {

  @Id
  private String id;
  private String name;
  private List<String> studentsId;
  private String teacherId;


  /**
   * The method is used to change the name of a class.
   *
   * @param name the name of the class.
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * set teacher id.
   *
   * @param teacherId TeacherId
   */
  public void setTeacherId(final String teacherId) {
    this.teacherId = teacherId;
  }


  /**
   * @param studentsId the List of studentsId present in the class.
   */
  public void setStudentsId(List<String> studentsId) {
    this.studentsId = studentsId;
  }
}
