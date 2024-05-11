package com.example.demo.model.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowedCard {
    private int idBorrowedCard;
    private Date borrowedDate;
    private Date returnDate;
    private int bookNum;
    private Student student;
    
}
