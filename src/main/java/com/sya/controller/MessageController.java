package com.sya.controller;


import com.sya.common.Pagination;
import com.sya.model.User;
import com.sya.request.CreateMessageDto;
import com.sya.request.MessageIdDto;
import com.sya.service.AuthenticationService;
import com.sya.service.MessageService;
import com.sya.view.Message;
import com.sya.view.MessageInfo;
import com.sya.view.MessageInfoPage;
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
        return new MessageInfo(messageService.CreateNormalMessage(createMessageDto,user));
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
    public MessageInfoPage FindSendMessage(
            @RequestBody Pagination pagination,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }
        return messageService.FindSendMessage(user,pagination);
    }

    @PostMapping("FindReceiveMessage")
    @ResponseBody
    public MessageInfoPage FindReceiveMessage(
            @RequestBody Pagination pagination,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }
        return messageService.FindReceiveMessage(user,pagination,0);
    }

    @PostMapping("FindReceiveResign")
    @ResponseBody
    public MessageInfoPage FindReceiveResign(
            @RequestBody Pagination pagination,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }
        return messageService.FindReceiveMessage(user,pagination,1);
    }


}
