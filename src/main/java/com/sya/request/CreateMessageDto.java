package com.sya.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageDto {

    @JsonProperty("message_type")
    private Integer messageType;

    @JsonProperty("content")
    private String Content;

    @JsonProperty("receiver_id")
    private Integer receiverId;

}
