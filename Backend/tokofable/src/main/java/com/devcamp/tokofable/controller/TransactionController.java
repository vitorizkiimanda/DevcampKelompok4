package com.devcamp.tokofable.controller;

import com.devcamp.tokofable.entity.Transactions;
import com.devcamp.tokofable.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionsService transactionsService ;

    @PostMapping
    public Transactions create(@RequestBody Transactions transactions) {
        return transactionsService.create(transactions);
    }
}
