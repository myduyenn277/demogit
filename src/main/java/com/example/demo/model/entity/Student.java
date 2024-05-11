package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
// @Entity
// @Table(name = "Student")
public class Student {
    // @id
    // @Column(name="idStudent"")
    private int idStudent;
    private String name;
    private int age;
    private String clasS;
    
   
}
