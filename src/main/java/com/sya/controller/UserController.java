package com.sya.controller;

import com.sya.dao.UserDAO;
import com.sya.model.User;
import com.sya.service.AuthenticationService;
import com.sya.service.TakesService;
import com.sya.service.UserService;
import com.sya.view.AccountStatus;
import com.sya.view.Message;
import com.sya.view.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/User")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(path = "/GetUserInfo")
    public @ResponseBody
    Object getUserInfo(@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId){
        User user=authenticationService.getUser(sessionId);
        if(user==null){
            return new Message("sessionId is invalid!");
        }
        return userService.getUserInfo(user);
    }
}
