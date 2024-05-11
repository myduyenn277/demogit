package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.entity.Student;
import com.example.demo.model.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService = new StudentService();

    // @GetMapping(value = {"/"})
    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public String showList(Model model){
        ArrayList<Student> studentList = studentService.getAllStudent();
        model.addAttribute("studentList", studentList);
        return "studentIndex";
    }

    @GetMapping("/addStudent")
    public String showAdd(Model model){
        model.addAttribute("student", new Student());
        return "studentAdd";
    }

    @PostMapping("/addStudent/action")
    public String toAdd(@ModelAttribute("student") Student student, Model model){
        studentService.createStudent(student);
        return showAdd(model);
    }

    @GetMapping("/updateStudent/{idStudent}")
    public String showDetail(@PathVariable("idStudent") int idStudent, Model model){
        Student student = studentService.getStudentById(idStudent);
        model.addAttribute("student", student);
        return "studentView";
    }

    @PostMapping("/updateStudent/{idStudent}/action")
    public String toEdit(@ModelAttribute("idStudent") Student student, Model model){
        studentService.updateStudent(student);
        return showDetail(student.getIdStudent(), model);
    }

    @GetMapping("/deleteStudent/{idStudent}")
    public String toDelte(@PathVariable("idStudent") int id, Model model){
        studentService.deleteStudent(id);
        return showList(model);
    }
}
