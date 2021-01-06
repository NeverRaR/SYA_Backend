package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.model.MessageLibrary;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Data
public class MessageInfo {

    @JsonProperty("message_id")
    private Integer messageId;

    @JsonProperty("message_type")
    private Integer messageType;

    @JsonProperty("content")
    private String content;

    @JsonProperty("message_time")
    private Date messageTime;

    @JsonProperty("sender_id")
    private Integer senderId;

    @JsonProperty("recevier_id")
    private Integer recevierId;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("receiver_name")
    private String receiverName;

    public MessageInfo(MessageLibrary messageLibrary){

        setMessageId(messageLibrary.getId());
        setMessageType(messageLibrary.getMessageType());
        setContent(messageLibrary.getContent());
        setMessageTime(messageLibrary.getTime());
        setSenderId(messageLibrary.getSender().getId());
        setSenderName(messageLibrary.getSender().getUsername());
        setRecevierId(messageLibrary.getReceiver().getId());
        setReceiverName(messageLibrary.getReceiver().getUsername());
        setStatus(messageLibrary.getStatus());

    }


}
