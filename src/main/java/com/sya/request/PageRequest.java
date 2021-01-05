package com.sya.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageRequest {
    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    @JsonProperty("pagenum")
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty("pagesize")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
