package it.colletta.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private List<String> studentsId;
    private String teacher;
}