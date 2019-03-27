package it.colletta.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import java.util.List; 


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
    private List<UserModel> studentList; 
    private String teacher;

    /**
     * @param name TODO 
     * @return  TODO 
     */
    public void setName(String name) {
        this.name = name;
    }
    /*
    public void addStudent(@NonNull UserModel student) {
        studentList.add(student);
    }
    */
    
    /**
     * @param student TODO
     * @return TODO
     */
    /*
    public Boolean deleteStudent(@NonNull UserModel student){
        if(studentList.contains(student)){
                return studentList.remove(student);
        }
        else {
            return false;
        }    
    } 
    */
}