package com.devcamp.tokofable.service;

import com.devcamp.tokofable.config.AuthContext;
import com.devcamp.tokofable.entity.Transactions;
import com.devcamp.tokofable.entity.Users;
import com.devcamp.tokofable.exceptions.BadRequestException;
import com.devcamp.tokofable.repository.TransactionRepository;
import com.devcamp.tokofable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionsService {
    @Autowired
    private TransactionRepository transactionRepository ;
    @Autowired
    private UserRepository userRepository ;


    public List<Transactions> getAll(){
        return transactionRepository.findAll();
    }

    @Transactional
    public Transactions createBuy(Transactions transactions){
        validate(transactions);
        transactions.setTransactionType(true);
        transactions.setId(UUID.randomUUID().toString());
        transactions.setDate(new Date());
        transactions.setUser(new Users(AuthContext.getRequester()));
        Users users = userRepository.findById(AuthContext.getRequester()).orElse(null);
        if(users == null) {
            throw new BadRequestException("user tidak ditemukan");
        }
        if(users.getOvo() >= (transactions.getPrice()* transactions.getWeight())){
            users.setOvo(users.getOvo() - (transactions.getPrice()* transactions.getWeight()));
            users.setGoldWeight(users.getGoldWeight()+transactions.getWeight());
        }
        else
            throw new BadRequestException("saldo OVO tidak mencukupi");
        return transactionRepository.save(transactions);
    }

    @Transactional
    public Transactions createSell(Transactions transactions){
        validate(transactions);
        transactions.setTransactionType(false);
        transactions.setId(UUID.randomUUID().toString());
        transactions.setDate(new Date());
        transactions.setUser(new Users(AuthContext.getRequester()));
        Users users = userRepository.findById(AuthContext.getRequester()).orElse(null);
        if(users == null) {
            throw new BadRequestException("user tidak ditemukan");
        }
        if(users.getGoldWeight() >= transactions.getWeight()){
            users.setOvo(users.getOvo() + (transactions.getPrice()* transactions.getWeight()));
            users.setGoldWeight(users.getGoldWeight() - transactions.getWeight());
        }
        else
            throw new BadRequestException("berat emas tidak mencukupi");
        return transactionRepository.save(transactions);
    }

    private void validate(Transactions transactions){
        if(StringUtils.isEmpty(transactions.getPrice()))
            throw new BadRequestException("Harga harus di isi");
        if(StringUtils.isEmpty(transactions.getWeight()))
            throw new BadRequestException("Berat harus di isi");
    }
}
