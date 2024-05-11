package com.example.demo.Controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.entity.BorrowedCard;
import com.example.demo.model.entity.Student;
import com.example.demo.model.service.BorrowedCardService;
import com.example.demo.model.service.StudentService;

@Controller
@RequestMapping(value = "/borrowedCard")
public class BorrowedCardController {

    @Autowired
    BorrowedCardService borrowedCardService = new BorrowedCardService();
    @Autowired
    StudentService studentService = new StudentService();

    @GetMapping(value = { "/", "" })
    public String showList(Model model) {
        ArrayList<BorrowedCard> borrowedCardList = borrowedCardService.getAllBorrowedCard();
        model.addAttribute("borrowedCardList", borrowedCardList);
        return "borrowedCardIndex";
    }

    @GetMapping("/addBorrowedCard")
    public String showAdd(Model model) {
        model.addAttribute("borrowedCard", new BorrowedCard());
        model.addAttribute("studentList", studentService.getAllStudent());

        return "borrowedCardAdd";
    }

    @PostMapping("/addBorrowedCard/action")
    public String toAdd(@ModelAttribute("borrowedCard") BorrowedCard borrowedCard,
            @RequestParam("studentid") String studentId, @RequestParam("returnDate") String returnDateStr, Model model) {
        Student student = studentService.getStudentById(Integer.parseInt(studentId));
        borrowedCard.setStudent(student);
        Date borrowDate = Date.valueOf(LocalDate.now());
        Date dateFormat = Date.valueOf(returnDateStr);
        borrowedCard.setReturnDate(dateFormat);
        borrowedCard.setBorrowedDate(borrowDate);
        borrowedCardService.createBorrowedCard(borrowedCard);
        return showAdd(model);
    }

    @GetMapping("/updateBorrowedCard/{idBorrowedCard}")
    public String showDetail(@PathVariable("idBorrowedCard") int idBorrowedCard, Model model) {
        BorrowedCard borrowedCard = borrowedCardService.getBorrowedCardById(idBorrowedCard);
        model.addAttribute("borrowedCard", borrowedCard);
        return "borrowedCardView";
    }

    @PostMapping("/updateBorrowedCard/{idBorrowedCard}/action")
    public String toEdit(@ModelAttribute("idBorrowedCard") BorrowedCard borrowedCard, Model model) {
        borrowedCardService.updateBorrowedCard(borrowedCard);
        model.addAttribute("studentList", studentService.getAllStudent());
        return showDetail(borrowedCard.getIdBorrowedCard(), model);
    }

    @GetMapping("/deleteBorrowedCard/{idBorrowedCard}")
    public String toDelte(@PathVariable("idBorrowedCard") int id, Model model) {
        borrowedCardService.deleteBorrowedCard(id);
        return showList(model);
    }
}
