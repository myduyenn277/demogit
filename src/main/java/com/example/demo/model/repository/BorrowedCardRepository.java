package com.example.demo.model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.BorrowedCard;
import com.example.demo.model.entity.Student;

@Repository
public class BorrowedCardRepository {
    ArrayList<BorrowedCard> borrowedCardList = new ArrayList<>();
    StudentRepository studentRepository = new StudentRepository();
    public static void main(String[] args) {
        BorrowedCardRepository borrowedCardRepository = new BorrowedCardRepository();
        // Student student = new Student(6, "Vinh",21 , "DE170630");
        // BorrowedCard borrowedCard = new BorrowedCard(0, null, null, 0, student);
        // borrowedCardRepository.createBorrowedCard(borrowedCard);
        System.out.println(borrowedCardRepository.getBorrowedCardById(1));
        ;
    }

    public ArrayList<BorrowedCard> getAllBorrowedCard() {
        borrowedCardList.clear();
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            Statement stsm = con.createStatement();
            ResultSet rs = stsm.executeQuery("select * from borrowedCardPrj.BorrowedCard");
            while (rs.next()) {
                int idBorrowedCard = rs.getInt("idBorrowedCard");
                Date borrowedCard = rs.getDate("borrowedDate");
                Date returnDate = rs.getDate("returnDate");
                int bookNum = rs.getInt("bookNum");
                int idStudent = rs.getInt("idStudent");
                Student s = studentRepository.getStudentById(idStudent);
                BorrowedCard BorrowedCard = new BorrowedCard(idBorrowedCard, borrowedCard, returnDate, bookNum, s);
                borrowedCardList.add(BorrowedCard);
            }
            con.close();
            return borrowedCardList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public BorrowedCard getBorrowedCardById(int idBorrowedCard) {
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prsm = con
                    .prepareStatement("SELECT * FROM borrowedCardPrj.BorrowedCard where idBorrowedCard = ?;");
            prsm.setInt(1, idBorrowedCard);
            ResultSet rs = prsm.executeQuery();
            rs.next();
            Date borrowedCard = rs.getDate("borrowedDate");
            Date returnDate = rs.getDate("returnDate");
            int bookNum = rs.getInt("bookNum");
            int idStudent = rs.getInt("idStudent");
            Student s = studentRepository.getStudentById(idStudent);
            BorrowedCard BorrowedCard = new BorrowedCard(idBorrowedCard, borrowedCard, returnDate, bookNum, s);
            con.close();
            return BorrowedCard;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int createBorrowedCard(BorrowedCard borrowedCard) {
        int i = 0;
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prst = con.prepareStatement(
                    "insert into borrowedCardPrj.BorrowedCard(borrowedDate,returnDate, bookNum, idStudent) values ( ?, ?,?,?);");
            prst.setDate(1, borrowedCard.getBorrowedDate());
            prst.setDate(2, borrowedCard.getReturnDate());
            prst.setInt(3, borrowedCard.getBookNum());
            prst.setInt(4, borrowedCard.getStudent().getIdStudent());
            i = prst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    public int deleteBorrowedCard(int idBorrowedCard) {
        int i = 0;
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prst = con
                    .prepareStatement("delete from borrowedCardPrj.BorrowedCard where idBorrowedCard=?");
            prst.setInt(1, idBorrowedCard);
            i = prst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    public int updateBorrowedCard(BorrowedCard borrowedCard) {
        int i = 0;
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prst = con.prepareStatement(
                    "update borrowedCardPrj.BorrowedCard set borrowedDate = ?, returnDate = ?, bookNum = ?, idStudent=? where idBorrowedCard = ?;");
            prst.setDate(1, borrowedCard.getBorrowedDate());
            prst.setDate(2, borrowedCard.getReturnDate());
            prst.setInt(3, borrowedCard.getBookNum());
            prst.setInt(4, borrowedCard.getStudent().getIdStudent());
            i = prst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }
}
