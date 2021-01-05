package com.sya.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkIdRequest {
    private Integer workId;

    public Integer getWorkId() {
        return workId;
    }

    @JsonProperty("work_id")
    public void setWorkId(Integer workId) {
        this.workId = workId;
    }
}
