package com.sya.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WorkListView {
    private Integer totalPage;
    private Integer pageNum;
    private List<WorkStatus> workList;

    @JsonProperty("totalpage")
    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    @JsonProperty("pagenum")
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @JsonProperty("worklist")
    public List<WorkStatus> getWorkList() {
        return workList;
    }

    public void setWorkList(List<WorkStatus> workList) {
        this.workList = workList;
    }
}
