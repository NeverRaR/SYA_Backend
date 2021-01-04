package com.sya.controller;

import com.sya.model.User;
import com.sya.request.RegisterRequest;
import com.sya.service.AuthenticationService;
import com.sya.service.UserService;
import com.sya.view.RegisterView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/Account")
public class AccountController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @PostMapping(path = "/Register")
    public @ResponseBody
    RegisterView addNewUser (@RequestBody RegisterRequest registerRequest) {

        RegisterView registerView=new RegisterView();
        Integer id=userService.addNewUser(registerRequest);
        if(id == null){
            return registerView;
        }

        registerView.setId(id);
        registerView.setEmail(registerRequest.getEmail());
        registerView.setUsername(registerRequest.getUsername());
        registerView.setRole(registerRequest.getRole());
        return registerView;

    }




}
