package com.sya.controller;


import com.sya.model.User;
import com.sya.request.CreateMessageDto;
import com.sya.request.MessageIdDto;
import com.sya.service.AuthenticationService;
import com.sya.service.MessageService;
import com.sya.view.Message;
import com.sya.view.MessageInfo;
import com.sya.view.MessageInfoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Message")
public class MessageController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    MessageService messageService;

    @PostMapping("CreateMessage")
    @ResponseBody
    public Object CreateMessage(
            @RequestBody CreateMessageDto createMessageDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }
        if(user.getRole().equals(1)){
            return new Message("Student cannot create message");
        }
        return new MessageInfo(messageService.CreateMessage(createMessageDto,user));
    }

    @PostMapping("DeleteMessage")
    @ResponseBody
    public Object DeleteMessage(
            @RequestBody MessageIdDto messageIdDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        if(messageService.deleteMessage(messageIdDto)){
            return 2;
        }
        else {
            return new Message("Delete Failed!");
        }
    }

    @PostMapping("ViewedMessage")
    @ResponseBody
    public Object ViewedMessage(
            @RequestBody MessageIdDto messageIdDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        if(messageService.viewedMessage(messageIdDto)){
            return 1;
        }
        else {
            return new Message("Viewed Failed!");
        }
    }

    @PostMapping("FindSendMessage")
    @ResponseBody
    public MessageInfoList FindSendMessage(
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }
        return messageService.FindSendMessage(user);
    }

    @PostMapping("FindReceiveMessage")
    @ResponseBody
    public MessageInfoList FindReceiveMessage(
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }
        return messageService.FindReceiveMessage(user);
    }



}
