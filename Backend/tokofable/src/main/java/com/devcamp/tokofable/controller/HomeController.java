package com.devcamp.tokofable.controller;

import com.devcamp.tokofable.model.HomeModel;
import com.devcamp.tokofable.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService ;

    @GetMapping
    public HomeModel getHome() {
        return homeService.getHome();
    }

}
