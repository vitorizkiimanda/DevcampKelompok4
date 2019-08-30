package com.devcamp.tokofable.service;

import com.devcamp.tokofable.entity.Users;
import com.devcamp.tokofable.repository.UsersRepository;
import com.devcamp.tokofable.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository ;

    public Users register(Users users){
        users.setId(UUID.randomUUID().toString());
        users.setPassword(Security.hashPassword(users.getPassword()));
        return usersRepository.save(users);
    }

    public List<Users> getAll(){
        return usersRepository.findAll();
    }
}
