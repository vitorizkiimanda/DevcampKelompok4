package com.devcamp.tokofable.controller;

import com.devcamp.tokofable.entity.GoldPrices;
import com.devcamp.tokofable.service.GoldPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gold-price")
public class GoldPriceController {
    @Autowired
    private GoldPriceService goldPriceService ;

    @GetMapping
    public List<GoldPrices> getAll(){
        return goldPriceService.getAll() ;
    }

    @PostMapping
    public GoldPrices create(@RequestBody GoldPrices goldPrices) {
        return goldPriceService.create(goldPrices);
    }
}
