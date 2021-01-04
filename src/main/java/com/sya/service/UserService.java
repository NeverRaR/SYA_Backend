package com.sya.service;

import com.sya.dao.UserDAO;
import com.sya.model.User;
import com.sya.request.RegisterRequest;
import com.sya.util.HashHelper;
import com.sya.view.RegisterView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    @Transactional
    public Integer addNewUser (RegisterRequest registerRequest) {

        if(registerRequest.getRole() == 0) {
            return -1;
        }
        User user = userDAO.findByUsername(registerRequest.getUsername());
        if(user != null) {
            return -1;
        }
        user = new User();
        user.setUsername(registerRequest.getUsername());
        double seed= ThreadLocalRandom.current().nextDouble();
        user.setSalt(HashHelper.computeSha256Hash(user.getUsername()+ seed));
        user.setPassword(HashHelper.computeSha256Hash(user.getPassword()+user.getSalt()));
        user.setEmail(registerRequest.getEmail());
        user.setRegistered(new Date());

        userDAO.save(user);

        return user.getId();


    }
}
