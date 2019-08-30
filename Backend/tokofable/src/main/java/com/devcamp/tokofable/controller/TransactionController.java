package com.devcamp.tokofable.controller;

import com.devcamp.tokofable.config.AuthContext;
import com.devcamp.tokofable.entity.Transactions;
import com.devcamp.tokofable.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionsService transactionsService ;

    @GetMapping
    public List<Transactions> getAll(){
       return transactionsService.getAll();
    }

    @PostMapping("/buy")
    public Transactions createBuy(@RequestBody Transactions transactions) {
        return transactionsService.createBuy(transactions);
    }

    @PostMapping("/sell")
    public Transactions createSell(@RequestBody Transactions transactions) {
        return transactionsService.createSell(transactions);
    }

}
