package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;


@Data
@AllArgsConstructor
public class MessageInfoList {

    @JsonProperty("messageItem")
    private List<MessageInfo> messageInfoList;

    @JsonProperty("total")
    private Integer total;

}
