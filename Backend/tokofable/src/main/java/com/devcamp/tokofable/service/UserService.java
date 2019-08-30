package com.devcamp.tokofable.service;

import com.devcamp.tokofable.entity.Users;
import com.devcamp.tokofable.exceptions.BadRequestException;
import com.devcamp.tokofable.model.LoginModel;
import com.devcamp.tokofable.repository.UserRepository;
import com.devcamp.tokofable.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    @Value("${erp.salt}")
    private String SALT;

    @Autowired
    private UserRepository userRepository;

    public Users register(Users users){
        users.setId(UUID.randomUUID().toString());
        users.setPassword(Security.hashPassword(users.getPassword()));
        users.setGoldWeight(0.0);
        users.setOvo(50000.0);
        Users users1 = userRepository.findByEmail(users.getEmail());
        if(users1 != null)
            throw new BadRequestException("email sudah digunakan");
        return userRepository.save(users);
    }

    public List<Users> getAll(){
        return userRepository.findAll();
    }

    public LoginModel login(String email, String passowrd) {
        Users user = userRepository.findByEmail(email);
        if (user == null) throw new BadRequestException("Email tidak ditemukan");
        if (!Security.match(passowrd, user.getPassword()))
            throw new BadRequestException("Password tidak valid");
        String token = Security.createToken(user.getId(), "CUSTOMER", user.getName(), SALT);
        LoginModel loginModel = new LoginModel();
        loginModel.setId(user.getId());
        loginModel.setName(user.getName());
        loginModel.setToken(token);
        return loginModel;
    }
}
