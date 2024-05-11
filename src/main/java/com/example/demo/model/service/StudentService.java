package com.example.demo.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Student;
import com.example.demo.model.repository.StudentRepository;

@Service
public class StudentService {
     @Autowired
    StudentRepository studentRepository = new StudentRepository();
    ArrayList<Student> StudentList = studentRepository.getAllStudent();

    
    public ArrayList<Student> getAllStudent(){
        return studentRepository.getAllStudent();
    }

    public Student getStudentById(int id){
        return studentRepository.getStudentById(id);
    }

    public void createStudent(Student student){
        studentRepository.createStudent(student);
    }

    public void updateStudent(Student student){
        studentRepository.updateStudent(student);
    }

    public void deleteStudent(int id){
        studentRepository.deleteStudent(id);    
    }
}
