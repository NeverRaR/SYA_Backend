package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sya.common.Pagination;
import com.sya.model.Apply;
import com.sya.model.MessageLibrary;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ApplyInfoPage extends Pagination {

    @JsonProperty("applist")
    private List<ApplyInfo> applyInfoList;


    public ApplyInfoPage(Page<Apply> applyInfoPage) {
        setPageNum(applyInfoPage.getNumber()+1);
        setPageSize(applyInfoPage.getSize());
        setTotalPage(applyInfoPage.getTotalPages());
        setMessageInfoList(applyInfoPage);

    }

    private void setMessageInfoList(Page<Apply> applyInfoPage) {
        setApplyInfoList(
                applyInfoPage
                        .stream()
                        .map(ApplyInfo::new)
                        .collect(Collectors.toList())
        );
    }

    public void setApplyInfoList(List<ApplyInfo> applyInfoList) {
        this.applyInfoList = applyInfoList;
    }
}
