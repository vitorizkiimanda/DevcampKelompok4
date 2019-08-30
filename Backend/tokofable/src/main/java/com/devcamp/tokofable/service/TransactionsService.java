package com.devcamp.tokofable.service;

import com.devcamp.tokofable.entity.Transactions;
import com.devcamp.tokofable.exceptions.BadRequestException;
import com.devcamp.tokofable.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class TransactionsService {
    @Autowired
    private TransactionRepository transactionRepository ;

    public Transactions create(Transactions Transactions){
        validate(Transactions);
        Transactions.setId(UUID.randomUUID().toString());
        return transactionRepository.save(Transactions);
    }

    private void validate(Transactions Transactions){
        if(StringUtils.isEmpty(Transactions.getPrice()))
            throw new BadRequestException("Harga harus di isi");
    }
}
