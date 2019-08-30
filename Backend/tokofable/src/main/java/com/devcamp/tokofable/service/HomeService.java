package com.devcamp.tokofable.service;

import com.devcamp.tokofable.config.AuthContext;
import com.devcamp.tokofable.entity.GoldPrices;
import com.devcamp.tokofable.entity.Transactions;
import com.devcamp.tokofable.entity.Users;
import com.devcamp.tokofable.exceptions.BadRequestException;
import com.devcamp.tokofable.model.HomeModel;
import com.devcamp.tokofable.repository.GoldPriceRepository;
import com.devcamp.tokofable.repository.TransactionRepository;
import com.devcamp.tokofable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    private TransactionRepository transactionRepository ;
    @Autowired
    private GoldPriceRepository goldPriceRepository ;
    @Autowired
    private UserRepository userRepository ;

    public HomeModel getHome(){
        Users users = userRepository.findById(AuthContext.getRequester()).orElse(null);
        if(users == null)
            throw new BadRequestException("user tidak ditemukan");
        HomeModel homeModel = new HomeModel();
        GoldPrices goldPricesNow = goldPriceRepository.findByDate(getNormalizeDate(new Date()));
        if(goldPricesNow != null){
            homeModel.setPurchasePriceNow(goldPricesNow.getPurchasePrice());
            homeModel.setSellingPriceNow(goldPricesNow.getSellingPrice());
        }
        GoldPrices goldPricesYesterday = goldPriceRepository.findByDate(getNormalizeDate(new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L)));
        if(goldPricesYesterday != null){
            homeModel.setPurchasePriceYesterday(goldPricesYesterday.getPurchasePrice());
            homeModel.setSellingPriceYesterday(goldPricesYesterday.getSellingPrice());
        }
        homeModel.setTotalPrice(users.getGoldWeight()*goldPricesNow.getSellingPrice());
        homeModel.setTotalWeight(users.getGoldWeight());
        return homeModel ;
    }

    private Date getNormalizeDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
