package com.sya.service;

import com.sya.dao.ApplyDAO;
import com.sya.dao.SalaryDAO;
import com.sya.dao.TakesDAO;
import com.sya.dao.UserDAO;
import com.sya.model.Salary;
import com.sya.model.User;
import com.sya.request.RegisterRequest;
import com.sya.request.UpdateUserRequest;
import com.sya.util.HashHelper;
import com.sya.view.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    ApplyDAO applyDAO;

    @Autowired
    TakesDAO takesDAO;

    @Autowired
    SalaryDAO salaryDAO;

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

    @Transactional
    public User updateUser (User user, UpdateUserRequest body) {

        user.setBank(body.getBank());
        user.setAvatar(body.getAvatar());
        user.setGender(body.getGender());
        user.setTel(body.getTel());

        userDAO.save(user);

        return user;
    }

    public UserStatus getUserInfo(User user){
        UserStatus userStatus=new UserStatus();
        userStatus.setUser(user);
        userStatus.setApplyNum(applyDAO.countAllByStudent(user));
        userStatus.setAbsentNum(takesDAO.findAbsentNum(user.getId()));
        userStatus.setAbsentTime(takesDAO.findAbsentTime(user.getId()));
        userStatus.setWorkTime(takesDAO.findWorkTime(user.getId()));
        userStatus.setIncome(salaryDAO.findIncome(user.getId()));
        return userStatus;
    }

}
