package com.sya.controller;

import com.sya.dao.UserDAO;
import com.sya.model.User;
import com.sya.request.UpdateUserRequest;
import com.sya.service.AuthenticationService;
import com.sya.service.TakesService;
import com.sya.service.UserService;
import com.sya.view.AccountStatus;
import com.sya.view.Message;
import com.sya.view.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/UpdateUser")
    public @ResponseBody
    Object UpdateUser(@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId, @RequestBody UpdateUserRequest body){
        User user=authenticationService.getUser(sessionId);
        if(user==null){
            return new Message("sessionId is invalid!");
        }
        user = userService.updateUser(user,body);
        UserStatus userStatus=new UserStatus();
        userStatus.setUser(user);
        return userStatus;

    }
}
