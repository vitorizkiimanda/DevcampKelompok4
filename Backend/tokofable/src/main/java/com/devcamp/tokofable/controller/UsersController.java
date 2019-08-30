package com.devcamp.tokofable.controller;

import com.devcamp.tokofable.entity.Users;
import com.devcamp.tokofable.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService usersService ;

    @PostMapping("/register")
    public Users register(@RequestBody Users users) {
        return usersService.register(users);
    }

    @GetMapping
    public List<Users> getAll(){
        return usersService.getAll();
    }

}
