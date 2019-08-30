package com.devcamp.tokofable.controller;

import com.devcamp.tokofable.entity.Users;
import com.devcamp.tokofable.model.LoginModel;
import com.devcamp.tokofable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users users) {
        return userService.register(users);
    }

    @PostMapping("/login")
    public LoginModel resendVerificationCode(@RequestBody Map<String, String> param) {
        String email = param.get("email");
        String password = param.get("password");
        return userService.login(email, password);
    }

    @GetMapping
    public List<Users> getAll(){
        return userService.getAll();
    }

}
