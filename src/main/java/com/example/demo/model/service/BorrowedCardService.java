package com.example.demo.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.BorrowedCard;
import com.example.demo.model.repository.BorrowedCardRepository;

@Service
public class BorrowedCardService {
     @Autowired
    BorrowedCardRepository borrowedCardRepository = new BorrowedCardRepository();
    ArrayList<BorrowedCard> BorrowedCardList = borrowedCardRepository.getAllBorrowedCard();

    
    public ArrayList<BorrowedCard> getAllBorrowedCard(){
        return borrowedCardRepository.getAllBorrowedCard();
    }

    public BorrowedCard getBorrowedCardById(int id){
        return borrowedCardRepository.getBorrowedCardById(id);
    }

    public void createBorrowedCard(BorrowedCard BorrowedCard){
        borrowedCardRepository.createBorrowedCard(BorrowedCard);
    }

    public void updateBorrowedCard(BorrowedCard BorrowedCard){
        borrowedCardRepository.updateBorrowedCard(BorrowedCard);
    }

    public void deleteBorrowedCard(int id){
        borrowedCardRepository.deleteBorrowedCard(id);    
    }
}
