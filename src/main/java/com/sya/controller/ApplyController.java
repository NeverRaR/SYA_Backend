package com.sya.controller;

import com.sya.common.Pagination;
import com.sya.model.User;
import com.sya.request.CreateMessageDto;
import com.sya.request.ProManageDto;
import com.sya.request.WorkIdRequest;
import com.sya.service.ApplyService;
import com.sya.service.AuthenticationService;
import com.sya.view.Message;
import com.sya.view.MessageInfo;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Apply")
@RequiredArgsConstructor
public class ApplyController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ApplyService applyService;

    @PostMapping("CreateApply")
    @ResponseBody
    public Object CreateApply(
            @RequestBody WorkIdRequest workIdRequest,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }
        if(!user.getRole().equals(1)){
            return new Message("Only Student can Apply!");
        }
        return applyService.CreateApply(user,workIdRequest.getWorkId());
    }

    @PostMapping("ProViewApps")
    @ResponseBody
    public Object ProViewApps(
            @RequestBody Pagination pagination,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }
        if(user.getRole().equals(1)){
            return new Message("Students can't check!");
        }
        return applyService.ProViewApps(user,pagination);
    }


    @PostMapping("ProManageApp")
    @ResponseBody
    public Object ProManageApp(
            @RequestBody ProManageDto proManageDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }
        if(user.getRole().equals(1)){
            return new Message("Students cannot manage application.");
        }

        return applyService.ProManageApp(proManageDto);
    }



}
