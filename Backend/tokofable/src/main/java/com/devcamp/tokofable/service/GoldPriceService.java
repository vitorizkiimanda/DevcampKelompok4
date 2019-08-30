package com.devcamp.tokofable.service;

import com.devcamp.tokofable.entity.GoldPrices;
import com.devcamp.tokofable.exceptions.BadRequestException;
import com.devcamp.tokofable.repository.GoldPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GoldPriceService {
    @Autowired
    private GoldPriceRepository goldPriceRepository ;

    public List<GoldPrices> getAll(){
        return goldPriceRepository.findAll();
    }

    public GoldPrices create(GoldPrices goldPrices){
        validate(goldPrices);
        goldPrices.setId(UUID.randomUUID().toString());
        goldPrices.setDate(normalizeDate(goldPrices.getDate()));
        return goldPriceRepository.save(goldPrices);
    }

    private Date normalizeDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private void validate(GoldPrices goldPrices){
        if(StringUtils.isEmpty(goldPrices.getDate()))
            throw new BadRequestException("tanggal harus diisi");
        if(StringUtils.isEmpty(goldPrices.getPurchasePrice()))
            throw new BadRequestException("harga beli harus diisi");
        if(StringUtils.isEmpty(goldPrices.getSellingPrice()))
            throw new BadRequestException("harga jual harus diisi");
    }

}
