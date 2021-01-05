package com.sya.service;

import com.sya.dao.MessageDAO;
import com.sya.dao.UserDAO;
import com.sya.model.MessageLibrary;
import com.sya.model.User;
import com.sya.request.CreateMessageDto;
import com.sya.request.MessageIdDto;
import com.sya.view.MessageInfo;
import com.sya.view.MessageInfoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    MessageDAO messageDAO;

    @Transactional
    public MessageLibrary CreateMessage(CreateMessageDto createMessageDto, User user) {

        MessageLibrary message = new MessageLibrary();
        message.setMessageType(createMessageDto.getMessageType());
        message.setContentType(0);
        message.setContent(createMessageDto.getContent());
        message.setReceiver(userDAO.findUserById(createMessageDto.getReceiverId()));
        message.setSender(user);
        message.setStatus(0);
        message.setTime(new Date());
        messageDAO.save(message);
        return message;
    }

    @Transactional
    public Boolean deleteMessage(MessageIdDto messageIdDto) {

        if(messageIdDto.getMessageId()==null){
            return false;
        }

        if(messageDAO.findMessageLibraryById(messageIdDto.getMessageId())==null){
            return false;
        }

        messageDAO.deleteMessage(messageIdDto.getMessageId());

        return true;
    }

    @Transactional
    public Boolean viewedMessage(MessageIdDto messageIdDto) {

        if(messageIdDto.getMessageId()==null){
            return false;
        }

        MessageLibrary m = messageDAO.findMessageLibraryById(messageIdDto.getMessageId());

        if(m==null){
            return false;
        }
        m.setStatus(1);
        messageDAO.save(m);
        return true;
    }

    public MessageInfoList FindSendMessage(User user) {
        List<MessageLibrary> list = messageDAO.findAllBySender(user);
        List<MessageInfo> infoList = new ArrayList<>();
        for(MessageLibrary item : list){
            infoList.add(new MessageInfo(item));
        }
        MessageInfoList mInfoList = new MessageInfoList(
                infoList,
                messageDAO.countMessageLibrariesBySender(user)
        );
        return mInfoList;
    }


    public MessageInfoList FindReceiveMessage(User user) {
        List<MessageLibrary> list = messageDAO.findAllByReceiver(user);
        List<MessageInfo> infoList = new ArrayList<>();
        for(MessageLibrary item : list){
            infoList.add(new MessageInfo(item));
        }
        MessageInfoList mInfoList = new MessageInfoList(
                infoList,
                messageDAO.countMessageLibrariesByReceiver(user)
        );
        return mInfoList;
    }

}
