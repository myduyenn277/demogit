package com.example.demo.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Student;

@Repository
public class StudentRepository {
    ArrayList<Student> studentList = new ArrayList<>();
    // public static void main(String[] args) {
    //     StudentRepository studentRepository = new StudentRepository();
    //     System.out.println(studentRepository.getStudentById(6));
        
    //     // Student s = new Student();
    //     // s.getIdStudent();
    // }
    public ArrayList<Student> getAllStudent() {
        studentList.clear();
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            Statement stsm = con.createStatement();
            ResultSet rs = stsm.executeQuery("select * from borrowedCardPrj.Student");
            while (rs.next()) {
                int idStudent = rs.getInt("idStudent");
                String nameStudent = rs.getString("name");
                int age = rs.getInt("age");
                String clasS = rs.getString("clasS");
                Student Student = new Student(idStudent, nameStudent, age, clasS);
                studentList.add(Student);
            }
            con.close();
            return studentList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Student getStudentById(int idStudent) {
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prsm = con.prepareStatement("SELECT * FROM borrowedCardPrj.Student where idStudent = ?;");
            prsm.setInt(1, idStudent);
            ResultSet rs = prsm.executeQuery();
            rs.next();
            String nameStudent = rs.getString("name");
            int age = rs.getInt("age");
            String clasS = rs.getString("clasS");
            Student Student = new Student(idStudent, nameStudent, age, clasS);
            con.close();
            return Student;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int createStudent(Student student) {
        int i = 0;
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prst = con.prepareStatement(
                    "insert into borrowedCardPrj.Student(name,age, clasS) values (?, ?, ?);");
            prst.setString(1, student.getName());
            prst.setInt(2, student.getAge());
            prst.setString(3, student.getClasS());
            i = prst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    public int deleteStudent(int idStudent) {
        int i = 0;
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prst = con.prepareStatement("delete from borrowedCardPrj.Student where idStudent=?");
            prst.setInt(1, idStudent);
            i = prst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    public int updateStudent(Student student) {
        int i = 0;
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prst = con.prepareStatement(
                    "update borrowedCardPrj.Student set name = ?, age = ?, clasS = ? where idStudent = ?;");
            prst.setString(1, student.getName());
            prst.setInt(2, student.getAge());
            prst.setString(3, student.getClasS());
            prst.setInt(4, student.getIdStudent());
            i = prst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }
}
