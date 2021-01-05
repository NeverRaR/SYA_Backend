package com.sya.controller;

import com.sya.model.User;
import com.sya.request.LoginRequest;
import com.sya.request.RegisterRequest;
import com.sya.service.AuthenticationService;
import com.sya.service.UserService;
import com.sya.view.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path="/Account")
public class AccountController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @PostMapping(path = "/Register")
    public @ResponseBody
    AccountStatus addNewUser (@RequestBody RegisterRequest body) {

        User user=userService.addNewUser(body);

        return getAccountStatus(user);

    }

    @PostMapping(path = "/Login")
    public @ResponseBody
    AccountStatus login(@RequestBody LoginRequest body, HttpServletResponse response){
        String sessionId=authenticationService.createSessionId(body.getUsername(),body.getPassword());
        Cookie cookie=new Cookie("sessionId",sessionId);
        cookie.setMaxAge(3 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);

        User user=authenticationService.getUser(sessionId);
        return getAccountStatus(user);
    }

    @GetMapping(path = "/LoginStatus")
    public @ResponseBody
    AccountStatus getLoginStatus(@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId){
        User user=authenticationService.getUser(sessionId);
        return getAccountStatus(user);
    }

    @PostMapping(path = "/Logout")
    public @ResponseBody
    void logout(@CookieValue(value = "sessionId",
            defaultValue = "noSession") String sessionId,HttpServletResponse response){
        authenticationService.invalidateSessionId(sessionId);
        Cookie cookie=new Cookie("sessionId",null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    private AccountStatus getAccountStatus(User user) {
        AccountStatus accountStatus=new AccountStatus();
        if(user == null) {
            return accountStatus;
        }
        accountStatus.setUser(user);
        return accountStatus;
    }



}
