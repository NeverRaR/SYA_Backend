package com.sya.service;

import com.sya.common.Pagination;
import com.sya.dao.MessageDAO;
import com.sya.dao.UserDAO;
import com.sya.dao.WorkDAO;
import com.sya.model.MessageLibrary;
import com.sya.model.User;
import com.sya.request.CreateMessageDto;
import com.sya.request.MessageIdDto;
import com.sya.view.MessageInfo;
import com.sya.view.MessageInfoPage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    WorkDAO workDAO;

    @Transactional
    public MessageLibrary CreateNormalMessage(CreateMessageDto createMessageDto, User user) {

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
    public void CreateResignMessage(User sender, Integer workId) {

        MessageLibrary message_resign = new MessageLibrary();
        String content = sender.getUsername() + "同学辞去" +
        workDAO.findWorkById(workId).getName() + "工作";
        message_resign.setMessageType(0);
        message_resign.setContentType(1);
        message_resign.setContent(content);
        message_resign.setStatus(0);
        message_resign.setTime(new Date());
        message_resign.setSender(sender);
        message_resign.setReceiver(workDAO.findWorkById(workId).getTeacher());
        messageDAO.save(message_resign);
    }


    @Transactional
    public Boolean deleteMessage(MessageIdDto messageIdDto) {

        if(messageIdDto.getMessageId()==null){
            return false;
        }

        MessageLibrary m = messageDAO.findMessageLibraryById(messageIdDto.getMessageId());

        if(m==null || m.getStatus()==2){
            return false;
        }
        m.setStatus(2);

        messageDAO.save(m);

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

    public MessageInfoPage FindSendMessage(User user, Pagination pagination) {

        Page<MessageLibrary> list = messageDAO.findAllBySenderAndContentType(user,0,
                PageRequest.of(pagination.getPageNum() - 1, pagination.getPageSize()));

        MessageInfoPage mInfoList = new MessageInfoPage(list);
        return mInfoList;
    }

    public MessageInfoPage FindReceiveMessage(User user, Pagination pagination,Integer contentType) {
        Page<MessageLibrary> list = messageDAO.findAllByReceiverAndStatusNotAndContentType(user,2,
                contentType, PageRequest.of(pagination.getPageNum() - 1, pagination.getPageSize()
                )
        );

        MessageInfoPage mInfoList = new MessageInfoPage(list);
        return mInfoList;
    }

}
