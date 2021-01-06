package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.common.Pagination;
import com.sya.model.Favorite;
import com.sya.model.MessageLibrary;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
public class MessageInfoPage extends Pagination {

    @JsonProperty("messageItem")
    private List<MessageInfo> messageInfoList;


    public MessageInfoPage(Page<MessageLibrary> messageLibPage) {
        setPageNum(messageLibPage.getNumber()+1);
        setPageSize(messageLibPage.getSize());
        setTotalPage(messageLibPage.getTotalPages());
        setMessageInfoList(messageLibPage);

    }

    private void setMessageInfoList(Page<MessageLibrary> messageLibPage) {
        setMessageInfoList(
                messageLibPage
                        .stream()
                        .map(MessageInfo::new)
                        .collect(Collectors.toList())
        );
    }

    public void setMessageInfoList(List<MessageInfo> messageInfoList) {
        this.messageInfoList = messageInfoList;
    }

}
