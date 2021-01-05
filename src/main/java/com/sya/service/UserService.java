package com.sya.service;

import com.sya.dao.UserDAO;
import com.sya.model.User;
import com.sya.request.RegisterRequest;
import com.sya.util.HashHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    @Transactional
    public User addNewUser (RegisterRequest registerRequest) {

        if(registerRequest.getRole() == 0) {
            return null;
        }
        User user = userDAO.findByUsername(registerRequest.getUsername());
        if(user != null) {
            return null;
        }
        user = new User();
        user.setUsername(registerRequest.getUsername());
        double seed= ThreadLocalRandom.current().nextDouble();
        user.setSalt(HashHelper.computeSha256Hash(user.getUsername()+ seed));
        user.setPassword(HashHelper.computeSha256Hash(registerRequest.getPassword()+user.getSalt()));
        user.setEmail(registerRequest.getEmail());
        user.setRole(registerRequest.getRole());
        user.setRegistered(new Date());

        userDAO.save(user);

        return user;


    }
}
