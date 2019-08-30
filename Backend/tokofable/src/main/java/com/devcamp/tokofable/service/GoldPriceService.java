package com.devcamp.tokofable.service;

import com.devcamp.tokofable.entity.GoldPrices;
import com.devcamp.tokofable.repository.GoldPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoldPriceService {
    @Autowired
    private GoldPriceRepository goldPriceRepository ;

    public List<GoldPrices> getAll(){
        return goldPriceRepository.findAll();
    }
}
