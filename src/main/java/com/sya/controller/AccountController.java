package com.sya.controller;

import com.sya.model.User;
import com.sya.request.LoginRequest;
import com.sya.request.RegisterRequest;
import com.sya.service.AuthenticationService;
import com.sya.service.UserService;
import com.sya.view.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    AccountStatus login(@RequestBody LoginRequest body, HttpServletResponse response, HttpServletRequest request){
        String sessionId=authenticationService.createSessionId(body.getUsername(),body.getPassword());
        if(sessionId==null){
            response.setStatus(401);
            return null;
        }
        ResponseCookie responseCookie = ResponseCookie.from("sessionId", sessionId)
                .maxAge(3* 24* 60 * 60)
                .httpOnly(true)
                .path("/")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        User user=authenticationService.getUser(sessionId);
        return getAccountStatus(user);
    }

    @PostMapping(path = "/LoginStatus")
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
