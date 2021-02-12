package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CoinController {
    @Autowired
    private CoinRepo coinRepo;

    //http://localhost:2019/total
    @GetMapping(value="/total", produces= "application/json")
    public ResponseEntity<?> findTotal(){
        List<Coin> myList = new ArrayList<>();
        coinRepo.findAll().iterator().forEachRemaining(myList::add);
        double total = 0.0;
        for(Coin c: myList){
            total += c.getValue()*c.getQuantity();
            System.out.println(c.getQuantity()+ " "+ (c.getQuantity()> 1 ? c.getNameplural() : c.getName()));
        }
        System.out.println("The piggy bank holds "+ total);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}